package com.like.hrm.staffsalary.domain.model;

import java.util.List;

import com.like.hrm.staff.domain.model.Staff;

public interface Matchable {

	public boolean match(Staff staff, List<String> condition);
}
