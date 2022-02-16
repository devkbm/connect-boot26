package com.like.hrm.staff.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Comment;

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
	
	@Comment("직원명")
	@Column(name="STAFF_NAME")
	String name;	
		
	@Comment("직원명(영문)")
	@Column(name="STAFF_NAME_ENG")
	String nameEng;
		
	@Comment("직원명(한문)")
	@Column(name="STAFF_NAME_CHI")
	String nameChi;
	
	private StaffName(String name) {
		this.name = name;
	}
	
	public static StaffName of(String name) {
		return new StaffName(name);	
	}
	
	public static StaffName of(String name, String nameEng, String nameChi) {		 		
		return new StaffName(name, nameEng, nameChi);
	}	
	
} 
