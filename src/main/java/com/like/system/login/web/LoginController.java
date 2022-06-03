package com.like.system.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.login.boundary.LoginRequestDTO;
import com.like.system.login.domain.AuthenticationToken;
import com.like.system.login.service.LoginService;

@RestController
public class LoginController {		
		
	private LoginService service;		 	   
				
	public LoginController(LoginService service) {		
		this.service = service;
	}
		 
	@PostMapping("/common/user/login")
	public AuthenticationToken login(@RequestBody @Valid LoginRequestDTO dto, HttpSession session, HttpServletRequest request) {			
						         		 							                   
		String ipAddress = this.getClientIp(request);
		System.out.println("접속 IP주소: " + ipAddress);
		
		return service.login(dto, session);
	}	
	
    private String getClientIp(HttpServletRequest request) {
    	 
        String ip = request.getHeader("X-Forwarded-For");
          
        if (ip == null) ip = request.getHeader("Proxy-Client-IP");		// Proxy           
        if (ip == null) ip = request.getHeader("WL-Proxy-Client-IP"); 	// 웹로직
        if (ip == null) ip = request.getHeader("HTTP_CLIENT_IP");        
        if (ip == null) ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ip == null) ip = request.getRemoteAddr();
                       
        return ip; 
    }
        
    
}
