package com.like.system.biztypecode.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "BIZCODETYPE")
public class BizCodeType extends AbstractAuditEntity {

	@EmbeddedId
	BizCodeTypeId id;
	
	@Column(name="TYPE_NM")
	String name;
			
	@Column(name="PRT_SEQ")
	Integer sequence;
	
	@Enumerated(EnumType.STRING)
	@Column(name="BIZ_TYPE")
	BizTypeEnum bizType;
		
	@Embedded
	BizRuleComments ruleComments;
		
	@Column(name="CMT")
	String comment;
	
	@OneToMany(mappedBy="bizCodeType", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<BizCode> codes = new HashSet<>();
		
	public BizCodeType(String organizationCode, String typeId, String name, BizTypeEnum bizType, String comment) {
		if (!StringUtils.hasText(organizationCode)) throw new IllegalArgumentException("ID는 필수 입력 값입니다.");
		if (!StringUtils.hasText(typeId)) throw new IllegalArgumentException("ID는 필수 입력 값입니다."); 
		
		this.id = new BizCodeTypeId(organizationCode, typeId);
		this.name = name;		
		this.sequence = 0;
		this.bizType = bizType;
		this.comment = comment;
	}	
	
	public void modify(String name, Integer sequence, BizRuleComments ruleComments, String comment) {
		this.name = name;		
		this.sequence = sequence;
		this.ruleComments = ruleComments;
		this.comment = comment;
	}
	
	public BizCode getBizeCode(BizCodeId id) {
		return this.codes.stream()
						 .filter(e -> e.getId().equals(id))
						 .findFirst()
						 .orElse(null);
	}
	
	public void addBizCode(String code
				          ,String name
				          ,Boolean useYn
				          ,Integer sequence 
						  ,String comment) {
		this.codes.add(new BizCode(this, code, name, useYn, sequence, comment));
	}
	
	public void remove(BizCode code) {
		this.codes.remove(code);
	}
		
}
