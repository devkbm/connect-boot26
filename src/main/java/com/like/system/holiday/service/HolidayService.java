package com.like.system.holiday.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.holiday.domain.Holiday;
import com.like.system.holiday.domain.HolidayRepository;

@Service
@Transactional
public class HolidayService {

	private HolidayRepository repository;
	
	public HolidayService(HolidayRepository repository) {
		this.repository = repository;
	}
	
	public Holiday getHoliyday(LocalDate date) {
		return this.repository.findById(date).orElse(null);
	}
	
	public void saveHoliday(Holiday entity) {
		this.repository.save(entity);
	}
	
	public void deleteHoliday(LocalDate date) {		
		this.repository.deleteById(date);
	}
	
}
