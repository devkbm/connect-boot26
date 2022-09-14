package com.like.system.login.service;

import java.util.Collection;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.login.boundary.LoginRequestDTO;
import com.like.system.login.domain.AuthenticationToken;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserRepository;

@Transactional
@Service
public class LoginService {

	private AuthenticationManager authenticationManager;
	private SystemUserRepository userRepository;		 	
	
	public LoginService(AuthenticationManager authenticationManager
					   ,SystemUserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
	}
	
	public AuthenticationToken login(LoginRequestDTO dto, HttpServletRequest request) {
		String username = dto.getUsername();
		String password = dto.password();
		
		SystemUser user = userRepository.findById(username).orElseThrow(EntityNotFoundException::new);
		
		authentication(username, password, user.getAuthorities(), request);
		
		return AuthenticationToken.of(user, request);
	}
	
	public AuthenticationToken getAuthenticationToken(String userId, HttpServletRequest request) {
		SystemUser user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
		
		return AuthenticationToken.of(user, request);
	}
	
	private void authentication(String username, String password, Collection<? extends GrantedAuthority> collection, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, collection);
		
		token.setDetails(this.getAuthenticationToken(username, request));			
		
		Authentication authentication = authenticationManager.authenticate(token); 
							
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		
		// log.info(SecurityContextHolder.getContext().getAuthentication().getName() + " 로그인 되었습니다.");
		// log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
	}
}
