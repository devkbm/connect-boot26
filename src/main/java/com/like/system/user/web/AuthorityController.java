package com.like.system.user.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;
import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import com.like.system.core.message.MessageUtil;
import com.like.system.user.boundary.AuthorityDTO;
import com.like.system.user.domain.Authority;
import com.like.system.user.service.AuthorityService;

@RestController
public class AuthorityController {

	private AuthorityService service;
	
	public AuthorityController(AuthorityService service) {
		this.service = service;
	}		
	
	@GetMapping("/api/common/authority/{authorityId}")
	public ResponseEntity<?> getAuthority(@PathVariable String authorityId) {			
		
		Authority authority = service.getAuthority(authorityId);										
		
		return toOne(authority
				    ,MessageUtil.getQueryMessage(authority == null ? 0 : 1));
	}
		
	@PostMapping("/api/common/authority")
	public ResponseEntity<?> saveAuthority(@RequestBody AuthorityDTO.FormAuthority dto) {			
		
		service.createAuthority(dto);					
																				 				
		return toList(null							
				 	 ,MessageUtil.getSaveMessage(1));
	}	
	
	@DeleteMapping("/api/common/authority/{authorityId}")
	public ResponseEntity<?> deleteAuthority(@PathVariable String authorityId) {
		
		service.deleteAuthority(authorityId);					
			
		return toList(null							
				 	 ,MessageUtil.getDeleteMessage(1));
	}
}
