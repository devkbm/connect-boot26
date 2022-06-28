package com.like.system.core.message;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {
		
	private static MessageSource staticMessageSource;
	
	public MessageUtil(MessageSource messageSource) {		
		staticMessageSource = messageSource;
	}		
	
	public static String getQueryMessage(int count) {			
		return staticMessageSource.getMessage("common.query", new Integer[] { count }, Locale.getDefault());		
	}
		
	public static String getSaveMessage(int count) {
		return staticMessageSource.getMessage("common.save", new Integer[] { count }, Locale.getDefault());
	}
	
	public static String getDeleteMessage(int count) {
		return staticMessageSource.getMessage("common.delete", new Integer[] { count }, Locale.getDefault());
	}
}
