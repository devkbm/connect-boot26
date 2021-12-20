package com.like.system.biztypecode.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.system.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "BIZTYPECODE")
public class BizTypeCode extends AuditEntity {

	@Id
	@Column(name="TYPE_CODE")
	private String id;
	
	@Column(name="TYPE_CODE_NAME")
	private String name;
	
	@Column(name="USE_YN")
	private Boolean useYn = true;
	
	@Column(name="PRT_SEQ")
	private Integer sequence;
	
	@Enumerated(EnumType.STRING)
	@Column(name="BIZ_TYPE")
	private BizTypeEnum bizType;
		
	@Embedded
	BizRuleComments ruleComments;
		
	@Column(name="CMT")
	private String comment;
	
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
		
}
