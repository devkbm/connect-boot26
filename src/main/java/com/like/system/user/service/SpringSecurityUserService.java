package com.like.system.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.user.domain.SystemUserRepository;

@Transactional
@Service
public class SpringSecurityUserService implements UserDetailsService {

	private SystemUserRepository repository;
	
	public SpringSecurityUserService(SystemUserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {				
		return repository.findById(username)
						 .orElseThrow(() -> new UsernameNotFoundException(username + " is Not Found"));		
	}

}
