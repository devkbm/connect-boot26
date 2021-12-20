package com.like.system.biztypecode.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class BizRuleComments {

	@Column(name="BIZ_RULE_CMT_1")
	private String bizRuleComment1;
	
	@Column(name="BIZ_RULE_CMT_2")
	private String bizRuleComment2;
	
	@Column(name="BIZ_RULE_CMT_3")
	private String bizRuleComment3;
	
	@Column(name="BIZ_RULE_CMT_4")
	private String bizRuleComment4;
	
	@Column(name="BIZ_RULE_CMT_5")
	private String bizRuleComment5;
	
	@Column(name="BIZ_RULE_CMT_6")
	private String bizRuleComment6;
	
	@Column(name="BIZ_RULE_CMT_7")
	private String bizRuleComment7;
	
	@Column(name="BIZ_RULE_CMT_8")
	private String bizRuleComment8;
	
	@Column(name="BIZ_RULE_CMT_9")
	private String bizRuleComment9;
	
	@Column(name="BIZ_RULE_CMT_10")
	private String bizRuleComment10;
		
	public BizRuleComments(String bizRuleComment1) {
		this.bizRuleComment1 = bizRuleComment1;
	}
	
	public BizRuleComments(String bizRuleComment1
						  ,String bizRuleComment2) {
		this.bizRuleComment1 = bizRuleComment1;
		this.bizRuleComment2 = bizRuleComment2;		
	}
}
