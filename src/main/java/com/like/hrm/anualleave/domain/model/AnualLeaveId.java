package com.like.hrm.anualleave.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Comment;

import com.like.hrm.staff.domain.model.Staff;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AnualLeaveId implements Serializable {
	
	private static final long serialVersionUID = 7192154823642621593L;

	@Comment("직원번호")
	@Column(name="STAFF_ID")
	String staffId;
	
	@Comment("귀속년도")
	@Column(name="YYYY")
	Integer yyyy;			
	
	public AnualLeaveId(Staff staff, Integer yyyy) {
		this.staffId = staff.getId();
		this.yyyy = yyyy;
	}
}
