package com.like.hrm.staff.domain.model;

import java.util.List;

import com.like.hrm.staff.boundary.StaffDTO.SearchStaff;
import com.like.hrm.staff.boundary.ResponseStaffAppointmentRecord;

public interface StaffQueryRepository {

	List<Staff> getStaffList(SearchStaff dto);
		
	List<ResponseStaffAppointmentRecord> getStaffAppointmentRecordList(String staffId);
}
