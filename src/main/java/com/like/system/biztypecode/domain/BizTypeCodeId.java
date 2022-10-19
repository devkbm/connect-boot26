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
public class BizTypeCodeId implements Serializable {
	
	private static final long serialVersionUID = 3428517048766851878L;

	@Column(name="TYPE_ID")
	String typeId;
		
	@Column(name="CODE")
	String code;	
	
	public BizTypeCodeId(String typeId, String code) {
		this.typeId = typeId;
		this.code = code;
	}
}