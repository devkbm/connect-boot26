package com.like.hrm.staff.domain.model;

import java.util.List;

import com.like.hrm.staff.boundary.StaffDTO.SearchStaff;
import com.like.hrm.staff.boundary.ResponseStaffAppointmentRecord;
import com.like.hrm.staff.boundary.ResponseStaffCurrentAppointment;

public interface StaffQueryRepository {

	List<Staff> getStaffList(SearchStaff dto);
		
	List<ResponseStaffAppointmentRecord> getStaffAppointmentRecordList(String staffId);
	
	List<ResponseStaffCurrentAppointment> getStaffCurrentAppointment(String staffId);
}
