package com.like.system.hierarchycode.domain;

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
public class CodeId implements Serializable {
	
	private static final long serialVersionUID = 5066344742442647534L;

	@Column(name="SYSTEM_TYPE_CODE")
	String systemTypeCode;
	
	@Column(name="CODE_ID")
	String codeId;
	
	public CodeId(String systemTypeCode, String codeId) {
		this.systemTypeCode = systemTypeCode;		
		this.codeId = codeId;		
	}
	
	public CodeId(String systemTypeCode, Code parentCode, String code) {
		this.systemTypeCode = systemTypeCode;		
		
		if ( parentCode == null ) {
			this.codeId = code;			
		} else {
			this.codeId = parentCode.getId().getCodeId() + code;
		}
	}
}
