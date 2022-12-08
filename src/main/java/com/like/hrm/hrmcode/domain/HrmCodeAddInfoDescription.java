package com.like.hrm.hrmcode.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Embeddable
public class HrmCodeAddInfoDescription {

	@Column(name="TH1_ADD_INFO_DESC")
	String the1AddInfoDesc;
	
	@Column(name="TH2_ADD_INFO_DESC")
	String the2AddInfoDesc;
	
	@Column(name="TH3_ADD_INFO_DESC")
	String the3AddInfoDesc;
	
	@Column(name="TH4_ADD_INFO_DESC")
	String the4AddInfoDesc;
	
	@Column(name="TH5_ADD_INFO_DESC")
	String the5AddInfoDesc;
}
