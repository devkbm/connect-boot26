package com.like.system.user.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.user.domain.SystemUser;

@RestController
public class SpringSecurityExampleController {
	
	@GetMapping("/api/system/user/example1")
	public ResponseEntity<?> getEample1(Authentication authentication)  {								
		return toOne(authentication, MessageUtil.getQueryMessage(authentication == null ? 0 : 1));
	}
	

	@GetMapping("/api/system/user/example2")
	public ResponseEntity<?> getEample2(Principal principal) {								
		return toOne(principal, MessageUtil.getQueryMessage(principal == null ? 0 : 1));
	}
	
	@GetMapping("/api/system/user/example3")
	public ResponseEntity<?> getEample3(@AuthenticationPrincipal SystemUser user) {								
		return toOne(user, MessageUtil.getQueryMessage(user == null ? 0 : 1));
	}
}
