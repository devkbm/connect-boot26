package com.like.hrm.staff.domain.model;

import com.like.hrm.staff.boundary.StaffDTO.NewEmployee;

/**
 * 사번 생성 전략 인터페이스
 * 
 * @author cb457
 *
 */
public interface StaffIdCreateStrategy {
	public String create(NewEmployee emp);
}
