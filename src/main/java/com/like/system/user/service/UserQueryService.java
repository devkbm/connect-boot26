package com.like.system.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.user.boundary.UserDTO;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.UserQueryRepository;


@Service
@Transactional(readOnly = true)
public class UserQueryService  {

	private UserQueryRepository repository;
	
	public UserQueryService(UserQueryRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * 유저 도메인 리스트를 조회한다.
	 * @return	유저 도메인 리스트
	 */
	public List<SystemUser> getUserList(UserDTO.Search condition) {
		return repository.getUserList(condition);
	}		
	
}
