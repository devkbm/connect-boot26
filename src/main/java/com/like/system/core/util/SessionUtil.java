package com.like.system.core.util;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.like.system.login.domain.AuthenticationToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionUtil {
	
	/**
	 * 현재 로그인한 세션의 유저 아이디를 가져온다.
	 * @return String 유저아이디
	 */
	public static String getUserId() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	public static AuthenticationToken getAuthenticationToken(HttpSession session) {
						
		log.info(SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
		
		return null;
	}
}
