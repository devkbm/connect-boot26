package com.like.system.login.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.like.system.core.dto.HtmlSelectOptionRecord;
import com.like.system.user.domain.SystemUser;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthenticationToken implements Serializable {
		
	private static final long serialVersionUID = 7987811233360490990L;
	
	private String userId;
	private String userName;
	private String organizationCode;
	private String staffNo;
	private String email;
	private String imageUrl;
	private String token;
	private String oAuthAccessToken;
	private List<String> authorityList;
    private List<HtmlSelectOptionRecord> menuGroupList;
    
       
    @Builder
    public AuthenticationToken(String userId
    						  ,String userName
    						  ,String organizationCode
    						  ,String staffNo 
    						  ,String imageUrl
    						  ,String email
    						  ,String token
    						  ,String oAuthAccessToken
    						  ,List<String> authorityList
    						  ,List<HtmlSelectOptionRecord> menuGroupList) {
    	this.userId = userId;
    	this.organizationCode = organizationCode;    	
    	this.staffNo = staffNo;
        this.userName = userName;
        this.imageUrl = imageUrl;
        this.email = email;
        this.token = token;
        this.oAuthAccessToken = oAuthAccessToken;
        this.authorityList = authorityList;
        this.menuGroupList = menuGroupList;        
    }     
    
    public static AuthenticationToken of(SystemUser user, String sessionId) {
    	return AuthenticationToken
				.builder()
				.userId(user.getUsername())
				.userName(user.getName())
				.organizationCode(user.getOrganizationCode())
				.staffNo(user.getStaffNo())
				.imageUrl(user.getImage())
				.email(user.getEmail())
				.token(sessionId)
				.authorityList(user.getAuthorities().stream().map(e -> e.getAuthority()).toList())
				.menuGroupList(user.getMenuGroupList().stream().map(e -> new HtmlSelectOptionRecord(e.getName(), e.getId())).toList())
				.build();
    }
       
    public static void main(String[] vd) {
    	BCryptPasswordEncoder b = new BCryptPasswordEncoder();
    	System.out.println(b.encode("1234"));    	
    }
}
