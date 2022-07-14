package com.like.system.login.service;

import java.util.Collection;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

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
	
	public AuthenticationToken login(LoginRequestDTO dto, HttpSession session) {
		String username = dto.getUsername();
		String password = dto.password();
		
		SystemUser user = userRepository.findById(username).orElseThrow(EntityNotFoundException::new);
		
		authentication(username, password, user.getAuthorities(), session);
		
		return AuthenticationToken.of(user, session.getId());
	}
	
	public AuthenticationToken getAuthenticationToken(String userId, HttpSession session) {
		SystemUser user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
		
		return AuthenticationToken.of(user, session.getId());
	}
	
	private void authentication(String username, String password, Collection<? extends GrantedAuthority> collection, HttpSession session) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, collection);
		
		Authentication authentication = authenticationManager.authenticate(token); 
							
		SecurityContextHolder.getContext().setAuthentication(authentication); 						
		
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		
		// log.info(SecurityContextHolder.getContext().getAuthentication().getName() + " 로그인 되었습니다.");
		// log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
	}
}
