package com.like.hrm.staffsalary.domain.repository;

import com.like.hrm.staffsalary.domain.model.StaffSalary;

public interface StaffSalaryRepository {

	StaffSalary getStaffSalary();

	void saveStaffSalary();
	
	void deleteStaffSalary();
}
