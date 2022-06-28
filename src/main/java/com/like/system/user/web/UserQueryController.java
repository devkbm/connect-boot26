package com.like.system.user.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.core.web.util.ResponseEntityUtil;
import com.like.system.user.boundary.UserDTO;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.service.UserQueryService;

@RestController
public class UserQueryController {

	private UserQueryService service;
	
	public UserQueryController(UserQueryService service) {
		this.service = service;
	}
		
	@GetMapping("/api/common/user")
	public ResponseEntity<?> getUserList(UserDTO.SearchUser condition) throws FileNotFoundException, IOException {
				
		List<SystemUser> userList = service.getUserList(condition);						
		
		List<UserDTO.FormSystemUser> dtoList = userList.stream()
													   .map(user -> UserDTO.convertDTO(user))
													   .toList();
		
		/*
		List<UserDTO.FormSystemUser> dtoList = new ArrayList<>();
		
		for (SystemUser user : userList) {
			dtoList.add(UserDTO.convertDTO(user));
		}
		*/
		
		return ResponseEntityUtil.toList(dtoList
										,MessageUtil.getQueryMessage(dtoList.size()));
	}
}
