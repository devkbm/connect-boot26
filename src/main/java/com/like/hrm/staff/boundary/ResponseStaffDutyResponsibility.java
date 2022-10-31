package com.like.hrm.staff.boundary;

import java.time.LocalDate;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseStaffDutyResponsibility {

	String staffId;
	String staffNo;
	String staffName;
	Integer seq;
	String dutyResponsibilityCode;
	String dutyResponsibilityName;
	LocalDate fromDate;
	LocalDate toDate;
	Boolean isPayApply;
		
	@QueryProjection
	public ResponseStaffDutyResponsibility(String staffId
										  ,String staffNo
										  ,String staffName
										  ,Integer seq
										  ,String dutyResponsibilityCode
										  ,String dutyResponsibilityName
										  ,LocalDate fromDate
										  ,LocalDate toDate
										  ,Boolean isPayApply) {		
		this.staffId = staffId;
		this.staffNo = staffNo;
		this.staffName = staffName;
		this.seq = seq;
		this.dutyResponsibilityCode = dutyResponsibilityCode;
		this.dutyResponsibilityName = dutyResponsibilityName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.isPayApply = isPayApply;
	}
	
	
}
