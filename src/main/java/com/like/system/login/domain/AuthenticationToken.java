package com.like.system.login.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.like.system.menu.domain.MenuGroup;
import com.like.system.user.domain.SystemUser;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthenticationToken implements Serializable {
		
	private static final long serialVersionUID = 7987811233360490990L;
	
	private String userId;
	private String userName;
	private String email;
	private String imageUrl;
	private String token;
	private String oAuthAccessToken;
    //private Collection<? extends GrantedAuthority> authorities;
	private List<String> authorities;
    private List<MenuGroup> menuGroupList;
    
       
    @Builder
    public AuthenticationToken(String userId
    						  ,String userName
    						  ,String imageUrl
    						  ,String email
    						  ,String token
    						  ,String oAuthAccessToken
    						  ,List<String> collection
    						  ,List<MenuGroup> menuGroupList) {
    	this.userId = userId;
        this.userName = userName;
        this.imageUrl = imageUrl;
        this.email = email;
        this.token = token;
        this.oAuthAccessToken = oAuthAccessToken;
        this.authorities = collection;
        this.menuGroupList = menuGroupList;
        
    }     
    
    public static AuthenticationToken of(SystemUser user, String sessionId) {
    	return AuthenticationToken
				.builder()
				.userId(user.getUsername())
				.userName(user.getName())
				.imageUrl(user.getImage())
				.email(user.getEmail())
				.token(sessionId)
				.collection(user.getAuthorities().stream().map(o -> o.getAuthority()).toList())
				.menuGroupList(user.getMenuGroupList())				
				.build();
    }
       
    public static void main(String[] vd) {
    	BCryptPasswordEncoder b = new BCryptPasswordEncoder();
    	System.out.println(b.encode("1234"));    	
    }
}
