package com.like.hrm.salary.domain.model;

import java.util.List;

import com.like.hrm.staff.domain.model.Staff;

public interface Matchable {

	public boolean match(Staff employee, List<String> condition);
}
