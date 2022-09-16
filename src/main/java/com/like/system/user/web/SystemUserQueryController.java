package com.like.system.user.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.user.boundary.SystemUserDTO;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.service.SystemUserQueryService;

@RestController
public class SystemUserQueryController {

	private SystemUserQueryService service;
	
	public SystemUserQueryController(SystemUserQueryService service) {
		this.service = service;
	}
		
	@GetMapping("/api/system/user")
	public ResponseEntity<?> getUserList(SystemUserDTO.Search condition) throws FileNotFoundException, IOException {
				
		List<SystemUser> userList = service.getUserList(condition);						
		
		List<SystemUserDTO.FormSystemUser> dtoList = userList.stream()
													   		 .map(user -> SystemUserDTO.FormSystemUser.convertDTO(user))
													   		 .toList();
		
		/*
		List<UserDTO.FormSystemUser> dtoList = new ArrayList<>();
		
		for (SystemUser user : userList) {
			dtoList.add(UserDTO.convertDTO(user));
		}
		*/
		
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}
}
