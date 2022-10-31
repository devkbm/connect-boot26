package com.like.hrm.staff.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.boundary.ResponseStaffAppointmentRecord;
import com.like.hrm.staff.boundary.ResponseStaffCurrentAppointment;
import com.like.hrm.staff.boundary.ResponseStaffDutyResponsibility;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffQueryRepository;

@Service
@Transactional(readOnly = true)
public class StaffQueryService {

	private StaffQueryRepository repository;
	
	public StaffQueryService(StaffQueryRepository repository) {
		this.repository = repository;		
	}
	
	public List<Staff> getStaff(StaffDTO.SearchStaff dto) {
		return repository.getStaffList(dto);
	}
	
	public List<ResponseStaffAppointmentRecord> getStaffAppointmentRecordList(String staffId) {
		return repository.getStaffAppointmentRecordList(staffId);
	}
	
	public ResponseStaffCurrentAppointment getStaffCurrentAppointment(String staffId) {
		return repository.getStaffCurrentAppointment(staffId);
	}
	
	public List<ResponseStaffDutyResponsibility> getStaffDutyResponsibility(String staffId) {
		return repository.getStaffDutyResponsibility(staffId);
	}
}
