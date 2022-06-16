package com.like.system.holiday.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.holiday.domain.Holiday;
import com.like.system.holiday.domain.HolidayQueryRepository;
import com.like.system.holiday.domain.service.DateInfo;
import com.like.system.holiday.domain.service.DateInfoList;

@Service
public class DateInfoService {

	HolidayQueryRepository holidayRepository;
	
	public DateInfoService(HolidayQueryRepository holidayRepository) {
		this.holidayRepository = holidayRepository;
	}
		
	public DateInfoList getDateInfoList(LocalDate fromDate, LocalDate toDate) {
		List<DateInfo> days = this.getRawDateInfoList(fromDate, toDate);
		
		List<Holiday> holidays = this.getHolidayList(fromDate, toDate);
		
		return new DateInfoList(days, holidays);
	}				
		
	private List<Holiday> getHolidayList(LocalDate fromDate, LocalDate toDate) {
		return holidayRepository.getHoliday(fromDate, toDate);
	}
	/*
	private Map<LocalDate, Holiday> toHashMap(List<Holiday> holidays) {
		return holidays.stream().collect(Collectors.toMap(Holiday::getDate, holiday -> holiday));
	}
	*/
	private List<DateInfo> getRawDateInfoList(LocalDate fromDate, LocalDate toDate) {
		if (fromDate.isAfter(toDate)) 
			throw new IllegalArgumentException("종료일자보다 시작일자가 큽니다.");
		
		final long dayCnt = fromDate.until(toDate, ChronoUnit.DAYS);
		List<DateInfo> list = new ArrayList<>(Math.toIntExact(dayCnt));			
		
		while (fromDate.isBefore(toDate) || fromDate.isEqual(toDate)) {
			list.add(new DateInfo(fromDate));			
			fromDate = fromDate.plusDays(1);
		}
		
		return list;
	}
	
	
}
