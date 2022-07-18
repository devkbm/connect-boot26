package com.like.system.user.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.user.boundary.AuthorityDTO;
import com.like.system.user.domain.Authority;
import com.like.system.user.service.AuthorityQueryService;

@RestController
public class AuthorityQueryController {

	private AuthorityQueryService service;
	
	public AuthorityQueryController(AuthorityQueryService service) {
		this.service = service;				
	}
	
	@GetMapping("/api/common/authority")
	public ResponseEntity<?> getAuthorityList(AuthorityDTO.SearchAuthority dto) {				
		
		List<Authority> authorityList = service.getAuthorityList(dto);								 							
		
		return toList(authorityList, MessageUtil.getQueryMessage(authorityList.size()));
	}
}
