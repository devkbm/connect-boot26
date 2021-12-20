package com.like.hrm.staff.domain.model;

import com.like.hrm.staff.boundary.StaffDTO.NewEmployee;

public class ManualStaffIdCreateStrategy implements StaffIdCreateStrategy  {

	@Override
	public String create(NewEmployee emp) {
		return emp.getName();
	}

}
