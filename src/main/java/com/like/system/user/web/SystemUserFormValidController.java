package com.like.system.user.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.user.domain.Authority;
import com.like.system.user.service.AuthorityService;
import com.like.system.user.service.SystemUserService;

@RestController
public class SystemUserFormValidController {
	
	private SystemUserService userService;
	private AuthorityService authorityService;
		
	public SystemUserFormValidController(SystemUserService userService
							  			,AuthorityService authorityService) {
		this.userService = userService;
		this.authorityService = authorityService;
	}

	@GetMapping("/api/system/user/{userId}/check")
	public ResponseEntity<?> checkId(@PathVariable String userId) {
						
		boolean isDuplicated = userService.CheckDuplicationUser(userId);					
				
		return toOne(isDuplicated ? false : true, isDuplicated ? "기존 아이디가 존재합니다." : "신규 등록 가능합니다."); 
	}
		
	@GetMapping("/api/system/authority/{authorityName}/check")
	public ResponseEntity<?> getAuthorityDupCheck(@PathVariable String authorityName) {			
					
		Authority authority = authorityService.getAuthority(authorityName);										
		
		boolean rtn = authority == null ? true : false;
						
		return toOne(rtn, rtn == false? "기존에 등록된 권한이 존재합니다." : "신규 등록 가능합니다.");
	}
}
