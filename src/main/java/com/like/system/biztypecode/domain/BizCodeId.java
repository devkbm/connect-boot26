package com.like.system.biztypecode.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class BizCodeId implements Serializable {
	
	private static final long serialVersionUID = 3428517048766851878L;

	BizCodeTypeId bizCodeTypeId;	
		
	@Column(name="CODE")
	String code;	
	
	public BizCodeId(String organizationCode, String typeId, String code) {
		this.bizCodeTypeId = new BizCodeTypeId(organizationCode, typeId);		
		this.code = code;
	}
}
