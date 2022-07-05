package com.like.system.user.domain;

import java.util.List;

import com.like.system.user.boundary.SystemUserDTO;

public interface SystemUserQueryRepository {

	/**
	 * 유저 도메인 리스트를 조회한다.
	 * @return	유저 도메인 리스트
	 */
	List<SystemUser> getUserList(SystemUserDTO.Search condition);
	
}
