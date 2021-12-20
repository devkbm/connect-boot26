package com.like.system.core.util;

import java.time.LocalDate;

public class LocalDateUtil {	
	
	public static LocalDate MAX_DATE = LocalDate.of(9999, 12, 31);
	
	/**
	 * 
	 * @param date 기준일자
	 * @param compareToDate 비교대상일자
	 * @return boolean
	 */
	public static boolean isBeforeOrEqual(LocalDate date, LocalDate compareToDate) {	    	    
	    return !date.isAfter(compareToDate);
	}

	public static boolean isAfterOrEqual(LocalDate date, LocalDate compareToDate) {	   
	    return !date.isBefore(compareToDate);
	}
	
	public static boolean isBetween(LocalDate dt, LocalDate fromDate, LocalDate toDate) {
		return isAfterOrEqual(dt, fromDate) && isBeforeOrEqual(dt, toDate);
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
