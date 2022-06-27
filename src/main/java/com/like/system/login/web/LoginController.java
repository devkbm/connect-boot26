package com.like.system.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.util.SessionUtil;
import com.like.system.login.boundary.LoginRequestDTO;
import com.like.system.login.domain.AuthenticationToken;
import com.like.system.login.service.LoginService;

@RestController
public class LoginController {		
		
	private LoginService service;
	
	private static final String[] IP_HEADER_CANDIDATES = {
	        "X-Forwarded-For",
	        "Proxy-Client-IP",
	        "WL-Proxy-Client-IP",
	        "HTTP_X_FORWARDED_FOR",
	        "HTTP_X_FORWARDED",
	        "HTTP_X_CLUSTER_CLIENT_IP",
	        "HTTP_CLIENT_IP",
	        "HTTP_FORWARDED_FOR",
	        "HTTP_FORWARDED",
	        "HTTP_VIA",
	        "REMOTE_ADDR" };   
	
	public LoginController(LoginService service) {		
		this.service = service;
	}
		 
	@PostMapping("/common/user/login")
	public AuthenticationToken login(@RequestBody @Valid LoginRequestDTO dto, HttpSession session, HttpServletRequest request) {			
						         		 							                   
		String ipAddress = this.getClientIpAddress(request);
		System.out.println("접속 IP주소: " + ipAddress);
		
		return service.login(dto, session);
	}	
	
	@GetMapping("/api/user/auth")
	public AuthenticationToken get(HttpSession session) {
		return service.getAuthenticationToken(SessionUtil.getUserId(), session);
	}
	
	public String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
         
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
	       
    
}
