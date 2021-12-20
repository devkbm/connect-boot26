package com.like.hrm.staff.domain.model;

import com.like.hrm.staff.boundary.StaffDTO.NewStaff;

/**
 * 사번 생성 전략 인터페이스
 * 
 * @author cb457
 *
 */
public interface StaffIdCreateStrategy {
	public String create(NewStaff emp);
}
