package com.like.system.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.user.boundary.AuthorityDTO;
import com.like.system.user.domain.Authority;
import com.like.system.user.domain.AuthorityRepository;

@Transactional
@Service
public class AuthorityService {

	private AuthorityRepository repository;
	
	public AuthorityService(AuthorityRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * 권한 도메인을 조회한다.
	 * @param id	권한명
	 * @return	권한 도메인
	 */
	public Authority getAuthority(String id) {
		return repository.findById(id).orElse(null);
	}
	
	/**
	 * 권한 도메인을 등록한다.
	 * @param authority	권한 도메인
	 */
	public void createAuthority(AuthorityDTO.FormAuthority dto) {
		Authority authority = null;
		
		if (dto.authorityId() != null) {
			authority = repository.findById(dto.authorityId()).orElse(null);
		} 
		
		if (authority == null) {
			authority = dto.newEntity();
		} else {
			dto.modifyEntity(authority);
		}
		
		repository.save(authority);
	}		
		
	/**
	 * 권한 도메인을 삭제한다.
	 * @param id
	 */
	public void deleteAuthority(String id) {
		repository.deleteById(id);
	}
}
