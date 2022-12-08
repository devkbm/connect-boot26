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
public class HrmCodeAddInfo {

	@Column(name="TH1_ADD_INFO")
	String the1AddInfo;
	
	@Column(name="TH2_ADD_INFO")
	String the2AddInfo;
	
	@Column(name="TH3_ADD_INFO")
	String the3AddInfo;
	
	@Column(name="TH4_ADD_INFO")
	String the4AddInfo;
	
	@Column(name="TH5_ADD_INFO")
	String the5AddInfo;
}
