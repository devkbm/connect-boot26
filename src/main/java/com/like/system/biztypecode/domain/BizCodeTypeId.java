package com.like.system.biztypecode.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class BizCodeTypeId implements Serializable {
	
	private static final long serialVersionUID = 6644923358649112843L;

	@Column(name="ORG_CD")
	String organizationCode;
		
	@Column(name="TYPE_ID")
	String typeId;
}
