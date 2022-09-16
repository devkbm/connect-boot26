package com.like.system.core.p6spylog;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

import org.hibernate.engine.jdbc.internal.FormatStyle;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class P6spyPrettySqlFormatter implements MessageFormattingStrategy {

	// 표기에 허용되지 않는 filter
    private List<String> DENIED_FILTER = Arrays.asList("Test1", this.getClass().getSimpleName());
    // 표기에 허용되는 filter
    private String ALLOW_FILTER = "com.like";
	
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
    	sql = formatSql(category, sql);
    	Date currentDate = new Date();

        SimpleDateFormat format1 = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
        
        return format1.format(currentDate) + " | " + sql + createStack(connectionId, elapsed);
    }

    private String formatSql(String category, String sql) {
        if(sql == null || sql.trim().equals("")) return sql;

        // Only format Statement, distinguish DDL And DML
        if (Category.STATEMENT.getName().equals(category)) {
            String tmpsql = sql.trim().toLowerCase(Locale.ROOT);
            if(tmpsql.startsWith("create") || tmpsql.startsWith("alter") || tmpsql.startsWith("comment")) {
                sql = FormatStyle.DDL.getFormatter().format(sql);
            } else {
                sql = FormatStyle.BASIC.getFormatter().format(sql);
            }
            sql = "|\nFormatSql(P6Spy sql,Hibernate format):"+ sql;
        }

        return sql;
    }
    
    // stack 콘솔 표기
    private String createStack(int connectionId, long elapsed) {
        Stack<String> callStack = new Stack<>();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        for (StackTraceElement stackTraceElement : stackTrace) {
            String trace = stackTraceElement.toString();

            // trace 항목을 보고 내게 맞는 것만 필터
            if(trace.startsWith(ALLOW_FILTER) && !filterDenied(trace)) {
                callStack.push(trace);
            }
        }

        StringBuffer sb = new StringBuffer();
        int order = 1;
        while (callStack.size() != 0) {
            sb.append("\n\t\t" + (order++) + " " + callStack.pop());
        }

        return new StringBuffer().append("\n\n\tConnection ID:").append(connectionId)
				                 .append(" | Excution Time:").append(elapsed).append(" ms\n")
				                 .append("\n\tExcution Time:").append(elapsed).append(" ms\n")
				                 .append("\n\tCall Stack :").append(sb).append("\n")
				                 .append("\n--------------------------------------")
				                 .toString();
    }

	private boolean filterDenied(String trace) {		
		for (String filter : this.DENIED_FILTER) {
			if (trace.startsWith(filter)) 
				return true;  
		}
		
		return false;
	}
}