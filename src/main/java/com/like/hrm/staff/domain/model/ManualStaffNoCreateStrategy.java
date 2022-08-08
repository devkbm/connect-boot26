package com.like.hrm.staff.domain.model;

import com.like.hrm.staff.boundary.StaffDTO.NewStaff;

public class ManualStaffNoCreateStrategy implements StaffNoCreateStrategy  {

	private Object param;
	
	public ManualStaffNoCreateStrategy(Object param) {
		this.param = param;
	}
	
	@Override
	public String create() {
		
		if (this.param instanceof NewStaff s) {
			return s.staffNo();
		} 
					
		return "";
	}



}
