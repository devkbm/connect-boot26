package com.like.hrm.staff.domain.model;

import java.util.List;

import com.like.hrm.staff.boundary.StaffDTO.SearchStaff;
import com.like.hrm.staff.boundary.ResponseStaffAppointmentRecord;
import com.like.hrm.staff.boundary.ResponseStaffCurrentAppointment;
import com.like.hrm.staff.boundary.ResponseStaffDutyResponsibility;

public interface StaffQueryRepository {

	
	List<Staff> getStaffList(SearchStaff dto);
	
	ResponseStaffCurrentAppointment getStaffCurrentAppointment(String staffId);
		
	List<ResponseStaffAppointmentRecord> getStaffAppointmentRecordList(String staffId);
	
	List<ResponseStaffDutyResponsibility> getStaffDutyResponsibility(String staffId);
	
}
