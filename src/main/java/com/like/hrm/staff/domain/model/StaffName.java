package com.like.hrm.staff.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class StaffName {

	/**
	 * 한글 성명
	 */
	@Column(name="STAFF_NAME")
	String name;
	
	/**
	 * 법적 이름
	 */
	@Column(name="STAFF_NAME_LEGAL")
	String legalName;
	
	/**
	 * 영문 성명
	 */
	@Column(name="STAFF_NAME_ENG")
	String nameEng;
	
	/**
	 * 한문 성명
	 */
	@Column(name="STAFF_NAME_CHI")
	String nameChi;
	
	private StaffName(String name) {
		this.name = name;
	}
	
	public static StaffName of(String name) {
		return new StaffName(name);	
	}
	
	public static StaffName of(String name, String legalName, String nameEng, String nameChi) {		 		
		return new StaffName(name, legalName, nameEng, nameChi);
	}	
	
} 
