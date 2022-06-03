package com.like.system.user.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.user.boundary.UserDTO;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.service.UserQueryService;

@RestController
public class UserQueryController {

	private UserQueryService service;
	
	public UserQueryController(UserQueryService service) {
		this.service = service;
	}
	
	@GetMapping(value={"/api/common/user"})
	public ResponseEntity<?> getUserList(UserDTO.SearchUser condition) throws FileNotFoundException, IOException {
				
		List<SystemUser> userList = service.getUserList(condition);						
		
		List<UserDTO.FormSystemUser> dtoList = new ArrayList<>();
		
		for (SystemUser user : userList) {
			dtoList.add(UserDTO.convertDTO(user));
		}
		
		return WebControllerUtil.getResponse(dtoList							
											,String.format("%d 건 조회되었습니다.", dtoList.size()));
	}
}
