package com.like.system.user.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.core.util.SessionUtil;
import com.like.system.user.boundary.PasswordChangeRequestDTO;
import com.like.system.user.boundary.SystemUserDTO;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.service.SystemUserService;

@RestController
public class SystemUserController {		
				
	private SystemUserService userService;
		
	public SystemUserController(SystemUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/api/common/user/myinfo")
	public ResponseEntity<?> getUserInfo() throws FileNotFoundException, IOException {
														
		SystemUser user = userService.getUser(SessionUtil.getUserId());				
		
		SystemUserDTO.FormSystemUser dto = SystemUserDTO.FormSystemUser.convertDTO(user);					
		
		return toOne(dto, MessageUtil.getQueryMessage(1));
	}
	
	@GetMapping("/api/common/user/{userId}")
	public ResponseEntity<?> getUser(@PathVariable String userId) throws FileNotFoundException, IOException {
						
		SystemUser user = userService.getUser(userId);				
		
		SystemUserDTO.FormSystemUser dto = SystemUserDTO.FormSystemUser.convertDTO(user);					
		
		return toOne(dto, MessageUtil.getQueryMessage(1));
	}		
	
	@PostMapping("/api/common/user")	
	public ResponseEntity<?> saveUser(@Valid @RequestBody SystemUserDTO.FormSystemUser dto) {			
											
		userService.saveUser(dto);					
																					 		
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
	
	@DeleteMapping("/api/common/user/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId) {
										
		userService.deleteUser(userId);															
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
		
	@PostMapping("/api/common/user/{id}/changepassword")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequestDTO dto) {				
						
		userService.changePassword(dto.userId(), dto.beforePassword(), dto.afterPassword());													
								 					
		return toList(null, "??????????????? ?????????????????????.");
	}
			
	@PostMapping("/api/common/user/{userId}/initpassword")
	public ResponseEntity<?> initializePassword(@PathVariable String userId) {			
				
		userService.initPassword(userId);														
								 					
		return toList(null, "??????????????? ????????????????????????.");
	}	
			
}
