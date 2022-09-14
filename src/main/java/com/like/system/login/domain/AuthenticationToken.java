package com.like.system.login.domain;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.like.system.core.dto.HtmlSelectOptionRecord;
import com.like.system.core.web.util.WebRequestUtil;
import com.like.system.user.domain.SystemUser;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthenticationToken implements Serializable {
		
	private static final long serialVersionUID = 7987811233360490990L;
	
	private String organizationCode;
	private String userId;
	private String userName;	
	private String staffNo;
	private String email;
	private String imageUrl;
	private String ipAddress;
	private String token;
	private String oAuthAccessToken;
	private List<String> authorityList;
    private List<HtmlSelectOptionRecord> menuGroupList;
    
       
    @Builder
    public AuthenticationToken(String organizationCode
    						  ,String userId
    						  ,String userName    						  
    						  ,String staffNo 
    						  ,String email
    						  ,String imageUrl
    						  ,String ipAddress
    						  ,String token
    						  ,String oAuthAccessToken
    						  ,List<String> authorityList
    						  ,List<HtmlSelectOptionRecord> menuGroupList) {
    	
    	this.organizationCode = organizationCode;
    	this.userId = userId;
    	this.userName = userName;    	
    	this.staffNo = staffNo;        
    	this.email = email;
    	this.imageUrl = imageUrl;
    	this.ipAddress = ipAddress;
        this.token = token;
        this.oAuthAccessToken = oAuthAccessToken;
        this.authorityList = authorityList;
        this.menuGroupList = menuGroupList;        
    }     
    
    public static AuthenticationToken of(SystemUser user, HttpServletRequest request) {
    	String ipAddress = request == null ? "TEST" : WebRequestUtil.getIpAddress(request);
    	    	
    	return AuthenticationToken
				.builder()
				.organizationCode(user.getOrganizationCode())
				.userId(user.getUsername())
				.userName(user.getName())				
				.staffNo(user.getStaffNo())
				.email(user.getEmail())
				.imageUrl(user.getImage())
				.ipAddress(ipAddress)
				.token(request.getSession().getId())
				.authorityList(user.getAuthorities().stream().map(e -> e.getAuthority()).toList())
				.menuGroupList(user.getMenuGroupList().stream().map(e -> new HtmlSelectOptionRecord(e.getName(), e.getId())).toList())
				.build();
    }
       
    public static void main(String[] vd) {
    	BCryptPasswordEncoder b = new BCryptPasswordEncoder();
    	System.out.println(b.encode("1234"));    	
    }
}
