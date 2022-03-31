package com.like.system.core.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.LongStream;

public class LocalDateUtil {	
	
	public static LocalDate MAX_DATE = LocalDate.of(9999, 12, 31);
	
	/**
	 * date <= compareToDate
	 * @param date 기준일자
	 * @param compareToDate 비교대상일자
	 * @return boolean
	 */
	public static boolean isBeforeOrEqual(LocalDate date, LocalDate compareToDate) {	    	    
	    return !date.isAfter(compareToDate);
	}

	/**
	 * date >= compareToDate
	 * @param date 기준일자
	 * @param compareToDate 비교대상일자
	 * @return
	 */
	public static boolean isAfterOrEqual(LocalDate date, LocalDate compareToDate) {	   
	    return !date.isBefore(compareToDate);
	}
	
	/**
	 * fromDate <= dt <= toDate
	 * @param dt 기준일자
	 * @param fromDate 시작일자
	 * @param toDate 종료일자
	 * @return
	 */
	public static boolean isBetween(LocalDate dt, LocalDate fromDate, LocalDate toDate) {
		return isAfterOrEqual(dt, fromDate) && isBeforeOrEqual(dt, toDate);
	}
	
	/**
	 * 시작일자 ~ 종료일자까지의 LocalDate를 리턴
	 * @param start 시작일자
	 * @param end 종료일자
	 * @return
	 */
	public static List<LocalDate> toLocalDateList(LocalDate start, LocalDate end) {
		if (start.isAfter(end)) throw new IllegalArgumentException("종료일자보다 시작일자가 큽니다.");
		
		final long days = start.until(end, ChronoUnit.DAYS);

		return LongStream.rangeClosed(0, days)
					     .mapToObj(start::plusDays)
					     .toList();					     
	}
	
	
	public static void main(String agrs[]) {
		
		System.out.println("작거나 같다 테스트");
		System.out.println(LocalDateUtil.isBeforeOrEqual(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 2)));
		System.out.println(LocalDateUtil.isBeforeOrEqual(LocalDate.of(2020, 1, 2), LocalDate.of(2020, 1, 2)));
		System.out.println(LocalDateUtil.isBeforeOrEqual(LocalDate.of(2020, 1, 3), LocalDate.of(2020, 1, 2)));
		
		System.out.println("크거나 같다 테스트");
		System.out.println(LocalDateUtil.isAfterOrEqual(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 2)));
		System.out.println(LocalDateUtil.isAfterOrEqual(LocalDate.of(2020, 1, 2), LocalDate.of(2020, 1, 2)));
		System.out.println(LocalDateUtil.isAfterOrEqual(LocalDate.of(2020, 1, 3), LocalDate.of(2020, 1, 2)));
		
		//System.out.println(LocalDateUtil.isAfterOrEqual(LocalDate.of(2020, 1, 1), null));
		
		LocalDate d1 = LocalDate.of(2021, 3, 1);
		LocalDate d2 = LocalDate.of(2021, 1, 1);
		LocalDate d3 = LocalDate.of(2021, 12, 1);
		System.out.println(LocalDateUtil.isBetween(d1, d2, d3));
		System.out.println(LocalDateUtil.isBetween(d2, d1, d3));
		System.out.println(LocalDateUtil.isBetween(d3, d2, d1));
	}
}
