package com.like.hrm.staffsalary.domain.model;

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
public class SalaryAppointmentInformation {

	@Comment("소속부서코드")
	@Column(name="BLNG_DEPT_CODE")
	String blngDeptCode;
		
	@Comment("근무부서코드")
	@Column(name="WORK_DEPT_CODE")
	String workDeptCode;
		
	@Comment("직군코드")
	@Column(name="JOB_GROUP_CODE")
	String jobGroupCode;
		
	@Comment("직위코드")
	@Column(name="JOB_POSITION_CODE")
	String jobPositionCode;
		
	@Comment("직종코드")
	@Column(name="OCCUPATION_CODE")
	String occupationCode;
		
	@Comment("직급코드")
	@Column(name="JOB_GRADE_CODE")
	String jobGradeCode;
		
	@Comment("호봉코드")
	@Column(name="PAY_STEP_CODE")
	String payStepCode;
		
	@Comment("직무코드")
	@Column(name="JOB_CODE")
	String jobCode;
	
	@Comment("직책코드")
	@Column(name="DUTY_RESPONSIBILITY_CODE")
	String dutyResponsibilityCode;
	
	public SalaryAppointmentInformation(String blngDeptCode
									   ,String workDeptCode
									   ,String jobGroupCode
									   ,String jobPositionCode
									   ,String occupationCode
									   ,String jobGradeCode
									   ,String payStepCode
									   ,String jobCode
									   ,String dutyResponsibilityCode
									   ) {
		this.blngDeptCode = blngDeptCode;
		this.workDeptCode = workDeptCode;
		this.jobGroupCode = jobGroupCode;
		this.jobPositionCode = jobPositionCode;
		this.occupationCode = occupationCode;
		this.jobGradeCode = jobGradeCode;
		this.payStepCode = payStepCode;
		this.jobCode = jobCode;
		this.dutyResponsibilityCode = dutyResponsibilityCode;
	}
		
}
