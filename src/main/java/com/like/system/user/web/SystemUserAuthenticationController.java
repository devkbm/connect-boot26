package com.like.system.user.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;

@RestController
public class SystemUserAuthenticationController {

	@GetMapping("/api/system/user/test")
	public ResponseEntity<?> getUser(Authentication authentication) throws FileNotFoundException, IOException {
						
		if (authentication != null) {
			// 세션 정보 객체 반환
			WebAuthenticationDetails web = (WebAuthenticationDetails)authentication.getDetails();
			System.out.println("세션ID : " + web.getSessionId());
			System.out.println("접속IP : " + web.getRemoteAddress());

			// UsernamePasswordAuthenticationToken에 넣었던 UserDetails 객체 반환
			//UserDetails userVO = (UserDetails) authentication.getPrincipal();
			System.out.println("ID정보 : " + authentication.getPrincipal().toString());
		}
		
		return toOne("", MessageUtil.getQueryMessage(1));
	}
}
