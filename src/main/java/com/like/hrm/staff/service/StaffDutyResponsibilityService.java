package com.like.hrm.staff.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.StaffDutyResponsibilityDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.dutyresponsibility.StaffDuty;

@Transactional
@Service
public class StaffDutyResponsibilityService {

	private StaffRepository repository;
	
	public StaffDutyResponsibilityService(StaffRepository repository) {
		this.repository = repository;
	}
	
	public StaffDuty get(String staffId, Integer seq) {
		Staff staff = this.repository.findById(staffId).orElse(null);
		
		StaffDuty entity = staff.getStaffDutyResponsibilityList().get(staff, seq);
		
		return entity;
	}
	
	public void save(StaffDutyResponsibilityDTO.Form dto) {
		Staff staff = this.repository.findById(dto.staffId()).orElse(null);
		
		StaffDuty entity = staff.getStaffDutyResponsibilityList().get(staff, dto.seq());
		
		if (entity == null) {
			entity = dto.newEntity(staff);
		}
		
		this.repository.save(staff);
	}
	
	
}
