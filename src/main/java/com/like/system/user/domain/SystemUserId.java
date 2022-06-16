package com.like.system.user.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class SystemUserId {

	@Comment("회사코드")
	@Column(name="COMPANY_CODE")
	String companyCode;
        
	@Comment("사용자 아이디")
	@Column(name="USER_ID")
    String userId;
}
