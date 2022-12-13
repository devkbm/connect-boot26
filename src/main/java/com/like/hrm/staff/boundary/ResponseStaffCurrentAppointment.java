package com.like.hrm.staff.boundary;

import com.like.hrm.hrmcode.domain.QHrmCode;
import com.like.hrm.staff.domain.model.QStaff;
import com.like.system.dept.domain.QDept;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseStaffCurrentAppointment {

	String staffId;
	String organizationCode;
	String staffNo;
	String blngDeptCode;
	String blngDeptName;
	String workDeptCode;
	String workDeptName;
	String jobGroupCode;
	String jobGroupName;
	String jobPositionCode;
	String jobPositionName;
	String occupationCode;
	String occupationName;
	String jobGradeCode;
	String jobGradeName;
	String payStepCode;
	String payStepName;
	String jobCode;
	String jobName;
	
	@QueryProjection
	public ResponseStaffCurrentAppointment(String staffId, String organizationCode, String staffNo, String blngDeptCode,
			String blngDeptName, String workDeptCode, String workDeptName, String jobGroupCode, String jobGroupName,
			String jobPositionCode, String jobPositionName, String occupationCode, String occupationName,
			String jobGradeCode, String jobGradeName, String payStepCode, String payStepName, String jobCode,
			String jobName) {		
		this.staffId = staffId;
		this.organizationCode = organizationCode;
		this.staffNo = staffNo;
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
	}
	
	public static QResponseStaffCurrentAppointment of(
			QStaff qStaff			
			,QDept blngDeptCode
			,QDept workDeptCode
			,QHrmCode jobGroupCode
			,QHrmCode jobPositionCode
			,QHrmCode occupationCode
			,QHrmCode jobGradeCode
			,QHrmCode payStepCode
			,QHrmCode jobCode) {
		return new QResponseStaffCurrentAppointment(
				qStaff.id
				,qStaff.organizationCode
				,qStaff.staffNo
				,qStaff.currentAppointment.blngDeptCode
				,blngDeptCode.deptNameKorean
				,qStaff.currentAppointment.workDeptCode
				,workDeptCode.deptNameKorean
				,qStaff.currentAppointment.jobGroupCode
				,jobGroupCode.codeName
				,qStaff.currentAppointment.jobPositionCode
				,jobPositionCode.codeName
				,qStaff.currentAppointment.occupationCode
				,occupationCode.codeName
				,qStaff.currentAppointment.jobGradeCode
				,jobGradeCode.codeName
				,qStaff.currentAppointment.payStepCode
				,payStepCode.codeName
				,qStaff.currentAppointment.jobCode
				,jobCode.codeName);
	}
	
}
