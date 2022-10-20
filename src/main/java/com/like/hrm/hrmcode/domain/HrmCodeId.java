package com.like.hrm.hrmcode.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class HrmCodeId implements Serializable {	
	
	private static final long serialVersionUID = -5420788999142741818L;

	@Column(name="TYPE_ID")
	String typeId;
		
	@Column(name="CODE")
	String code;	
	
	public HrmCodeId(String typeId, String code) {
		this.typeId = typeId;
		this.code = code;
	}
}
