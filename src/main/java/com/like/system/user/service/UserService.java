package com.like.system.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.DeptRepository;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuGroupRepository;
import com.like.system.user.boundary.UserDTO;
import com.like.system.user.domain.Authority;
import com.like.system.user.domain.AuthorityRepository;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class UserService {	
	
	private UserRepository repository;					
	private MenuGroupRepository menuRepository;	
	private DeptRepository deptRepository;		
	private AuthorityRepository authorityRepository;			
	
	public UserService(UserRepository repository
			  		  ,MenuGroupRepository menuRepository
			  		  ,DeptRepository deptRepository
			  		  ,AuthorityRepository authorityRepository) {
		this.repository = repository;
		this.menuRepository = menuRepository;
		this.deptRepository = deptRepository;
		this.authorityRepository = authorityRepository;		
	}
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();				
	
	/**
	 * 사용자 도메인을 조회한다.
	 * @param userId	사용자아이디
	 * @return 사용자 도메인
	 */
	public SystemUser getUser(String userId) {								
		return repository.findById(userId).orElse(null);
	}
		
	public SystemUser getFullUser(String userId) {
		SystemUser user = repository.findById(userId).orElse(null);
		List<MenuGroup> menuGroupList = user.getMenuGroupList();
		
		log.info(menuGroupList.toString());
		
		return user;				
	}
	
	/**
	 * 사용자를 생성한다.
	 * @param user	사용자 도메인
	 */
	public void saveUser(UserDTO.FormSystemUser dto) {
		SystemUser user = repository.findById(dto.getUserId()).orElse(null);
		Dept dept = dto.getDeptCode() == null ? null : deptRepository.findById(dto.getDeptCode()).orElse(null); 
		
		List<Authority> authorityList = authorityRepository.findAllById(dto.getAuthorityList());		
		List<MenuGroup> menuGroupList = menuRepository.findAllById(dto.getMenuGroupList());		 
						
		if (user == null) {
			user = dto.newUser(dept, authorityList, menuGroupList);
		} else {
			dto.modifyUser(user, dept, authorityList, menuGroupList);			
		}
		
		if (user.getUsername() == null) {
			new IllegalArgumentException("유저 아이디가 존재하지 않습니다.");
		}
		
		if ( user.getAuthoritiesList().isEmpty() ) {
			initAuthority(user);
		}			
		
		repository.save(user);
								
	}
		
	public void saveUser(SystemUser user) {
		repository.save(user);
	}
	
	/**
	 * 사용자 정보를 삭제한다.
	 * @param userId	사용자 아이디
	 */
	public void deleteUser(String userId) {
		repository.deleteById(userId);         
	}	
	
	/**
	 * 사용자 비밀번호를 변경한다.
	 * @param userId
	 * @param beforePassword	변경전 비밀번호
	 * @param afterPassword		변경후 비밀번호
	 */
	public void changePassword(String userId, String beforePassword, String afterPassword) {
		SystemUser user = repository.findById(userId).orElse(null);			
		
		if ( user.isVaild(beforePassword) ) {
			user.changePassword(afterPassword);
		} 
	}
	
	/**
	 * 사용자의 비밀번호를 초기화한다.
	 * @param userId	사용자 아이디
	 */
	public void initPassword(String userId) {
		SystemUser user = repository.findById(userId).orElse(null);
				
		user.initPassword();		
	}			
	
	/**
	 * 사용자 권한 리스트를 조회한다.
	 * @param userId	사용자아이디
	 * @return 사용자 권한 리스트
	 */
	public List<Authority> getUserAuthorities(String userId) {        									
        return repository.findById(userId).orElse(null).getAuthoritiesList();
	}
					
	
	/**
	 * 중복 유저 검증 기능
	 * @param userId
	 * @return 기존 아이디가 있으면 true, 아니면 false 리턴
	 */
	public boolean CheckDuplicationUser(String userId) {						
		return repository.existsById(userId); 
	}	
	
	public PasswordEncoder passwordEncoder(){
		return this.passwordEncoder;
	}
		
	
	/**
	 * 사용자 신규등록시 권한이 없을 경우 기본 권한을 추가한다.
	 * @param user	사용자 도메인
	 */
	private void initAuthority(SystemUser user) {							
		List<Authority> authorities = new ArrayList<Authority>();
		
		authorities.add(authorityRepository.findById("ROLE_USER").orElse(null));			
		user.setAuthorities(authorities);
	}
}
