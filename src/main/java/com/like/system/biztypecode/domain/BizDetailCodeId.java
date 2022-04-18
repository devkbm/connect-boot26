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
public class BizDetailCodeId implements Serializable {
	
	private static final long serialVersionUID = 3428517048766851878L;

	@Column(name="TYPE_CODE")
	String typeCode;
		
	@Column(name="DETAIL_CODE")
	String detailCode;	
	
	public BizDetailCodeId(String typeCode, String detailCode) {
		this.typeCode = typeCode;
		this.detailCode = detailCode;
	}
}
