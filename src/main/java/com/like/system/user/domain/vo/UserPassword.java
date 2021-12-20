package com.like.system.user.domain.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;

@Getter
@Embeddable
public class UserPassword implements Serializable {
	
	private static final long serialVersionUID = 5831655386795107265L;
	
	@Column(name="pwd")
	String password;
		
	public UserPassword() {
		this.init();
	}
	
	public UserPassword(String rawPassword) {
		this.password = rawPassword;
	}
	
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
	
	public void init() {
		this.password = "12345678";
	}
	
}
