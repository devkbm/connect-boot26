package com.like.hrm.staff.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.boundary.ResponseStaffAppointmentRecord;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffQueryRepository;

@Service
@Transactional
public class StaffQueryService {

	private StaffQueryRepository repository;
	
	public StaffQueryService(StaffQueryRepository repository) {
		this.repository = repository;		
	}
	
	public List<Staff> getEmployeeList(StaffDTO.SearchStaff dto) {
		return repository.getEmployeeList(dto);
	}
	
	public List<ResponseStaffAppointmentRecord> getStafflAppointmentRecordList(String staffId) {
		return repository.getStafflAppointmentRecordList(staffId);
	}
	
}
