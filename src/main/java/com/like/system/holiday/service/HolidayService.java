package com.like.system.holiday.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.holiday.boundary.HolidayDTO;
import com.like.system.holiday.domain.Holiday;
import com.like.system.holiday.domain.HolidayId;
import com.like.system.holiday.domain.HolidayRepository;

@Service
@Transactional
public class HolidayService {

	private HolidayRepository repository;
	
	public HolidayService(HolidayRepository repository) {
		this.repository = repository;
	}
	
	public Optional<Holiday> getHoliyday(String organizationCode, LocalDate date) {
		return this.repository.findById(new HolidayId(organizationCode, date));
	}
	
	public void saveHoliday(HolidayDTO.Form dto) {
		Holiday entity = this.getHoliday(dto);
		
		if (entity == null) {
			entity = dto.newEntity();
		} else {
			dto.modifyEntity(entity);
		}
		
		this.repository.save(entity);
	}
	
	public void deleteHoliday(String organizationCode, LocalDate date) {		
		this.repository.deleteById(new HolidayId(organizationCode, date));
	}
	
	private Holiday getHoliday(HolidayDTO.Form dto) {
		if (dto.date() == null) return null;
		
		return this.repository.findById(new HolidayId(dto.organizationCode(), dto.date())).orElse(null);
	}
	
}
