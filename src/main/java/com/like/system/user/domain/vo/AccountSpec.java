package com.like.system.user.domain.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class AccountSpec implements Serializable {
		
	private static final long serialVersionUID = 5321100631628293509L;

	/**
	 * 계정의 만료여부 : true (만료 안됨)
	 */
	@Column(name="non_expired_yn")
	Boolean isAccountNonExpired = true;
		
	/**
	 * 계정의 잠김 여부 : true (잠기지 않음)
	 */
	@Column(name="non_locked_yn")
	Boolean isAccountNonLocked = true;
		
	/**
	 * 비밀번호 만료 여부 : true (만료 안됨)
	 */
	@Column(name="pass_non_expired_yn")
	Boolean isCredentialsNonExpired = true;
		
	/**
	 * 계정의 활성화 여부 : true (활성화)
	 */
	@Column(name="enabled_yn")
	Boolean isEnabled = true;
	
	public AccountSpec(Boolean isAccountNonExpired
					  ,Boolean isAccountNonLocked
					  ,Boolean isCredentialsNonExpired
					  ,Boolean isEnabled) {
		
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;			
	}
}
