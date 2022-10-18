package com.like.hrm.hrmtypecode.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class HrmTypeDetailCodeId implements Serializable {
	
	private static final long serialVersionUID = 7109505610396778407L;

	@Column(name="TYPE_ID")
	String typeId;
		
	@Column(name="CODE")
	String code;	
	
	public HrmTypeDetailCodeId(String typeId, String code) {
		this.typeId = typeId;
		this.code = code;
	}
}
