package com.like.hrm.staff.boundary;

import java.time.LocalDate;

import com.like.hrm.hrmcode.domain.QHrmCode;
import com.like.hrm.staff.domain.model.QStaff;
import com.like.hrm.staff.domain.model.appointment.QAppointmentRecord;
import com.like.system.dept.domain.QDept;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseStaffAppointmentRecord {

	private String staffId;		
	private Long seq;			
	private String appointmentTypeCode;	
	private String appointmentTypeName;			
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
	public ResponseStaffAppointmentRecord(String staffId, Long seq, String appointmentTypeCode, String appointmentTypeName, LocalDate appointmentDate, LocalDate appointmentEndDate,
			String recordName, String comment, Boolean isCompleted, String blngDeptCode, String blngDeptName,
			String workDeptCode, String workDeptName, String jobGroupCode, String jobGroupName, String jobPositionCode,
			String jobPositionName, String occupationCode, String occupationName, String jobGradeCode,
			String jobGradeName, String payStepCode, String payStepName, String jobCode, String jobName,
			String dutyResponsibilityCode, String dutyResponsibilityName) {
		this.staffId = staffId;
		this.seq = seq;
		this.appointmentTypeCode = appointmentTypeCode;
		this.appointmentTypeName = appointmentTypeName;
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
	
	public static QResponseStaffAppointmentRecord of(
			 QStaff qStaff								
			,QAppointmentRecord qRecord
			,QDept blngDeptCode
			,QDept workDeptCode
			,QHrmCode appointmentTypeCode
			,QHrmCode jobGroupCode
			,QHrmCode jobPositionCode
			,QHrmCode occupationCode
			,QHrmCode jobGradeCode
			,QHrmCode payStepCode
			,QHrmCode jobCode
			,QHrmCode dutyResponsibilityCode) {

		return new QResponseStaffAppointmentRecord(
						QStaff.staff.id
					   ,qRecord.id.seq
					   ,qRecord.appointmentTypeCode
					   ,appointmentTypeCode.codeName
					   ,qRecord.appointmentDate
					   ,qRecord.appointmentEndDate
					   ,qRecord.recordName
					   ,qRecord.comment
					   ,qRecord.isCompleted
					   ,qRecord.info.blngDeptCode
					   ,blngDeptCode.deptNameKorean
					   ,qRecord.info.workDeptCode
					   ,workDeptCode.deptNameKorean
					   ,qRecord.info.jobGroupCode
					   ,jobGroupCode.codeName
					   ,qRecord.info.jobPositionCode
					   ,jobPositionCode.codeName
					   ,qRecord.info.occupationCode
					   ,occupationCode.codeName
					   ,qRecord.info.jobGradeCode
					   ,jobGradeCode.codeName
					   ,qRecord.info.payStepCode
					   ,payStepCode.codeName
					   ,qRecord.info.jobCode
					   ,jobCode.codeName
					   ,qRecord.info.dutyResponsibilityCode
					   ,dutyResponsibilityCode.codeName);
		}
	
	
}

