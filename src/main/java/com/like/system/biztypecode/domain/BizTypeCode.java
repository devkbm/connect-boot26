package com.like.system.biztypecode.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
@Table(name = "BIZTYPECODE")
public class BizTypeCode extends AbstractAuditEntity {

	@Id
	@Column(name="TYPE_CODE")
	String id;
	
	@Column(name="TYPE_CODE_NAME")
	String name;
	
	@Column(name="USE_YN")
	Boolean useYn = true;
	
	@Column(name="PRT_SEQ")
	Integer sequence;
	
	@Enumerated(EnumType.STRING)
	@Column(name="BIZ_TYPE")
	BizTypeEnum bizType;
		
	@Embedded
	BizRuleComments ruleComments;
		
	@Column(name="CMT")
	String comment;
	
	@OneToMany(mappedBy="bizTypeCode", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<BizDetailCode> codes = new HashSet<>();
	
	
	public BizTypeCode(String id, String name, BizTypeEnum bizType, String comment) {
		if (!StringUtils.hasText(id)) throw new IllegalArgumentException("ID는 필수 입력 값입니다."); 
		
		this.id = id;
		this.name = name;
		this.useYn = true;
		this.sequence = 0;
		this.bizType = bizType;
		this.comment = comment;
	}
	
	public BizTypeCode(String id, String name, Boolean useYn, Integer sequence, BizTypeEnum bizType, String comment) {
		this.id = id;
		this.name = name;
		this.useYn = useYn;
		this.sequence = sequence;
		this.bizType = bizType;
		this.comment = comment;
	}	
	
	public void modify(String name, Boolean useYn, Integer sequence, BizRuleComments ruleComments, String comment) {
		this.name = name;
		this.useYn = useYn;
		this.sequence = sequence;
		this.ruleComments = ruleComments;
		this.comment = comment;
	}
	
	public BizDetailCode getBizDetailCode(BizDetailCodeId id) {
		return this.codes.stream()
						 .filter(e -> e.getId().equals(id))
						 .findFirst()
						 .orElse(null);
	}
	
	public void addDetailCode(String code
				             ,String name
				             ,Boolean useYn
				             ,Integer sequence 
						     ,String comment) {
		this.codes.add(new BizDetailCode(this, code, name, useYn, sequence, comment));
	}
	
	public void remove(BizDetailCode code) {
		this.codes.remove(code);
	}
		
}
