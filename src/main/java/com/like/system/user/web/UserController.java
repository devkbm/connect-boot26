package com.like.system.user.web;

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

import com.like.system.core.util.SessionUtil;
import com.like.system.core.web.util.ResponseEntityUtil;
import com.like.system.user.boundary.PasswordChangeRequestDTO;
import com.like.system.user.boundary.UserDTO;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.service.UserService;

@RestController
public class UserController {		
				
	private UserService userService;
		
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/api/common/user/myinfo")
	public ResponseEntity<?> getUserInfo() throws FileNotFoundException, IOException {
														
		SystemUser user = userService.getUser(SessionUtil.getUserId());				
		
		UserDTO.FormSystemUser dto = UserDTO.convertDTO(user);					
		
		return ResponseEntityUtil.toOne(dto							
									   ,String.format("%d 건 조회되었습니다.", 1));
	}
	
	@GetMapping("/api/common/user/{userId}")
	public ResponseEntity<?> getUser(@PathVariable String userId) throws FileNotFoundException, IOException {
						
		SystemUser user = userService.getUser(userId);				
		
		UserDTO.FormSystemUser dto = UserDTO.convertDTO(user);					
		
		return ResponseEntityUtil.toOne(dto							
									   ,String.format("%d 건 조회되었습니다.", 1));
	}		
	
	@PostMapping("/api/common/user")	
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO.FormSystemUser dto) {			
											
		userService.saveUser(dto);					
																					 		
		return ResponseEntityUtil.toList(null							
										,String.format("%d 건 저장되었습니다.", 1));
	}	
	
	@DeleteMapping("/api/common/user/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId) {
										
		userService.deleteUser(userId);															
								 					
		return ResponseEntityUtil.toList(null							
										,String.format("%d 건 삭제되었습니다.", 1));
	}
		
	@PostMapping("/api/common/user/{id}/changepassword")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequestDTO dto) {				
						
		userService.changePassword(dto.userId(), dto.beforePassword(), dto.afterPassword());													
								 					
		return ResponseEntityUtil.toList(null							
										,"비밀번호가 변경되었습니다.");
	}
			
	@PostMapping("/api/common/user/{userId}/initpassword")
	public ResponseEntity<?> initializePassword(@PathVariable String userId) {			
				
		userService.initPassword(userId);														
								 					
		return ResponseEntityUtil.toList(null							
										,"비밀번호가 초기화되었습니다.");
	}	
			
}
