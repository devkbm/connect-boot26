package com.like.hrm.staff.domain.model.dutyresponsibility;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StaffDutyId implements Serializable {
	
	private static final long serialVersionUID = -1176225352272330423L;
	
	@Comment("직원ID")
	@Column(name="STAFF_ID")
	String staffId;
		
	@Comment("직책코드")
	@Column(name="DUTY_RESPONSIBILITY_CODE")
	String dutyResponsibilityCode;
		
	@Comment("시작일자")
	@Column(name="FROM_DT")
	LocalDate fromDate;
	
	public StaffDutyId(String staffId, String dutyResponsibilityCode, LocalDate fromDate) {
		this.staffId = staffId;
	}
}
