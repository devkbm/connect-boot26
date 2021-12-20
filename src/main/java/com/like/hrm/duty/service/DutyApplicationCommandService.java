package com.like.hrm.duty.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.duty.boundary.DutyApplicationDTO;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.repository.DutyApplicationRepository;
import com.like.system.holiday.domain.service.DateInfo;
import com.like.system.holiday.service.DateInfoService;

@Service
@Transactional
public class DutyApplicationCommandService {

	private DutyApplicationRepository repository;
	
	private DateInfoService holidayUtilService;
	
	public DutyApplicationCommandService(DutyApplicationRepository repository
			 							,DateInfoService holidayUtilService) {
		this.repository = repository;
		this.holidayUtilService = holidayUtilService;
	}
	
	public DutyApplication getDutyApplication(Long dutyId) {
		return repository.findById(dutyId).orElse(null);
	}
	
	public void saveDutyApplication(DutyApplicationDTO.SaveDutyApplication dto) {
		DutyApplication entity = null;
		
		if (dto.getDutyId() == null) {
			entity = dto.newEntity();
		} else {
			entity = this.getDutyApplication(dto.getDutyId());
			dto.modifyEntity(entity);
		}
		
		repository.save(entity);
	}
	
	public void deleteDutyApplication(Long dutyId) {
		repository.deleteById(dutyId);		
	}
	
	public List<DateInfo> getDateList(LocalDate fromDate, LocalDate toDate) {
		return this.holidayUtilService.getDateInfoList(fromDate, toDate).getDates();		
	}
	
}
