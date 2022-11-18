package com.like.hrm.staff.boundary;

import java.time.LocalDate;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseStaffAppointmentRecord {

	private String staffId;
		
	private Long seq;			
			
	private LocalDate appointmentDate;
			
	private LocalDate appointmentEndDate;
			
	private String recordName;
			
	private String comment;
			
	private Boolean isCompleted;
	
	private String blngDeptCode;
	
	private String blngDeptName;
	
	private String workDeptCode;
	
	private String workDeptName;
	
	private String jobGroupCode;
	
	private String jobGroupName;
	
	private String jobPositionCode;
	
	private String jobPositionName;
	
	private String occupationCode;
	
	private String occupationName;
	
	private String jobGradeCode;
	
	private String jobGradeName;
	
	private String payStepCode;
	
	private String payStepName;
	
	private String jobCode;
	
	private String jobName;
	
	private String dutyResponsibilityCode;
	
	private String dutyResponsibilityName;	
	
	@QueryProjection
	public ResponseStaffAppointmentRecord(String staffId, Long seq, LocalDate appointmentDate, LocalDate appointmentEndDate,
			String recordName, String comment, Boolean isCompleted, String blngDeptCode, String blngDeptName,
			String workDeptCode, String workDeptName, String jobGroupCode, String jobGroupName, String jobPositionCode,
			String jobPositionName, String occupationCode, String occupationName, String jobGradeCode,
			String jobGradeName, String payStepCode, String payStepName, String jobCode, String jobName,
			String dutyResponsibilityCode, String dutyResponsibilityName) {
		this.staffId = staffId;
		this.seq = seq;
		this.appointmentDate = appointmentDate;
		this.appointmentEndDate = appointmentEndDate;
		this.recordName = recordName;
		this.comment = comment;
		this.isCompleted = isCompleted;
		this.blngDeptCode = blngDeptCode;
		this.blngDeptName = blngDeptName;
		this.workDeptCode = workDeptCode;
		this.workDeptName = workDeptName;
		this.jobGroupCode = jobGroupCode;
		this.jobGroupName = jobGroupName;
		this.jobPositionCode = jobPositionCode;
		this.jobPositionName = jobPositionName;
		this.occupationCode = occupationCode;
		this.occupationName = occupationName;
		this.jobGradeCode = jobGradeCode;
		this.jobGradeName = jobGradeName;
		this.payStepCode = payStepCode;
		this.payStepName = payStepName;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.dutyResponsibilityCode = dutyResponsibilityCode;
		this.dutyResponsibilityName = dutyResponsibilityName;
	}
	
	
}

