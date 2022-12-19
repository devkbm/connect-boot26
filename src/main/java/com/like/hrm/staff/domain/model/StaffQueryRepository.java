package com.like.hrm.staff.domain.model;

import java.util.List;

import com.like.hrm.staff.boundary.StaffDTO.SearchStaff;
import com.like.hrm.staff.boundary.ResponseStaffAppointmentRecord;
import com.like.hrm.staff.boundary.ResponseStaffCard;
import com.like.hrm.staff.boundary.ResponseStaffCurrentAppointment;
import com.like.hrm.staff.boundary.ResponseStaffDutyResponsibility;
import com.like.hrm.staff.boundary.ResponseStaffFamily;
import com.like.hrm.staff.boundary.ResponseStaffLicense;
import com.like.hrm.staff.boundary.ResponseStaffSchoolCareer;

public interface StaffQueryRepository {

	
	List<Staff> getStaffList(SearchStaff dto);
	
	ResponseStaffCurrentAppointment getStaffCurrentAppointment(String staffId);
		
	List<ResponseStaffAppointmentRecord> getStaffAppointmentRecordList(String staffId);
	
	List<ResponseStaffDutyResponsibility> getStaffDutyResponsibility(String staffId);
	
	List<ResponseStaffFamily> getStaffFamily(String staffId);

	List<ResponseStaffSchoolCareer> getStaffSchoolCareer(String staffId);
	
	List<ResponseStaffLicense> getStaffLicense(String staffId);
	
	
	List<ResponseStaffCard> getStaffCard();
}
