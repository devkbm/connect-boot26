package com.like.system.core.jpa.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class PhoneNumber {

	@Column(name="PHONE_NO")
	private String number;
	
	public PhoneNumber(String number) {
		this.number = number;
	}
	
	public String getPhoneNumber() {
		return this.number;
	}
}
