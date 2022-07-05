package com.like.system.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.user.boundary.SystemUserDTO;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserQueryRepository;


@Service
@Transactional(readOnly = true)
public class SystemUserQueryService  {

	private SystemUserQueryRepository repository;
	
	public SystemUserQueryService(SystemUserQueryRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * 유저 도메인 리스트를 조회한다.
	 * @return	유저 도메인 리스트
	 */
	public List<SystemUser> getUserList(SystemUserDTO.Search condition) {
		return repository.getUserList(condition);
	}		
	
}
