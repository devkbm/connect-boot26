package com.like.system.user.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.core.web.util.ResponseEntityUtil;
import com.like.system.user.boundary.AuthorityDTO;
import com.like.system.user.domain.Authority;
import com.like.system.user.service.AuthorityService;

@RestController
public class AuthorityController {

	private AuthorityService service;
	
	public AuthorityController(AuthorityService service) {
		this.service = service;
	}		
	
	@GetMapping("/api/common/authority/{authorityName}")
	public ResponseEntity<?> getAuthority(@PathVariable String authorityName) {			
		
		Authority authority = service.getAuthority(authorityName);										
		
		return ResponseEntityUtil.toOne(authority
									   ,MessageUtil.getQueryMessage(authority == null ? 0 : 1));
	}
		
	@PostMapping("/api/common/authority")
	public ResponseEntity<?> saveAuthority(@RequestBody AuthorityDTO.FormAuthority dto) {			
		
		service.createAuthority(dto);					
																				 				
		return ResponseEntityUtil.toList(null							
										,MessageUtil.getSaveMessage(1));
	}	
	
	@DeleteMapping("/api/common/authority/{authorityName}")
	public ResponseEntity<?> deleteAuthority(@PathVariable String authorityName) {
		
		service.deleteAuthority(authorityName);					
			
		return ResponseEntityUtil.toList(null							
										,MessageUtil.getDeleteMessage(1));
	}
}
