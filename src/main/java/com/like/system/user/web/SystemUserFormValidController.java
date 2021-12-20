package com.like.system.user.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.user.domain.Authority;
import com.like.system.user.service.AuthorityService;
import com.like.system.user.service.UserService;

@RestController
public class SystemUserFormValidController {
	
	private UserService userService;
	private AuthorityService authorityService;
		
	public SystemUserFormValidController(UserService userService
							  ,AuthorityService authorityService) {
		this.userService = userService;
		this.authorityService = authorityService;
	}

	@GetMapping(value={"/api/common/user/{id}/check"})
	public ResponseEntity<?> checkId(@PathVariable(value="id") String userId) {
						
		boolean isDuplicated = userService.CheckDuplicationUser(userId);					
				
		return WebControllerUtil
				.getResponse(isDuplicated ? false : true
						    ,isDuplicated ? "기존 아이디가 존재합니다." : "신규 등록 가능합니다."
						    ,HttpStatus.OK); 
	}
		
	@GetMapping(value={"/api/common/authority/{id}/check"})
	public ResponseEntity<?> getAuthorityDupCheck(@PathVariable(value="id") String authorityName) {			
					
		Authority authority = authorityService.getAuthority(authorityName);										
		
		boolean rtn = authority == null ? true : false;
						
		return WebControllerUtil
				.getResponse(rtn
							,rtn == false? "기존에 등록된 권한이 존재합니다." : "신규 등록 가능합니다."
							,HttpStatus.OK);
	}
}
