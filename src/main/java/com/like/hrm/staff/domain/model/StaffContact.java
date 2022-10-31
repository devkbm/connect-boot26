package com.like.hrm.staff.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class StaffContact {

	@Comment("내선전화번호")
	@Column(name="EXTENSION_PHONE_NO")
	String extensionNumber;
	
	@Comment("모바일전화번호")
	@Column(name="MOBILE_PHONE_NO")
	String mobileNumber;
		
}
