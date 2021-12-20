package com.like.hrm.staff.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.like.hrm.staff.domain.model.appointment.AppointmentInformation;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author 김병민
 *
 */
// 용어 참고 https://terms.naver.com/list.naver?cid=51072&categoryId=51072
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class CurrentAppointmentInformation {
	
	/**
	 * 소속부서
	 */
	@Column(name="BLNG_DEPT_CODE")
	private String blngDeptCode;
	
	/**
	 * 근무부서
	 */
	@Column(name="WORK_DEPT_CODE")
	private String workDeptCode;
	
	/**
	 * 직군
	 */
	@Column(name="JOB_GROUP_CODE")
	private String jobGroupCode;
	
	/**
	 * 직위
	 */
	@Column(name="JOB_POSITION_CODE")
	private String jobPositionCode;
	
	/**
	 * 직종
	 */
	@Column(name="OCCUPATION_CODE")
	private String occupationCode;
	
	/**
	 * 직급
	 */
	@Column(name="JOB_GRADE_CODE")
	private String jobGradeCode;
	
	/**
	 * 호봉
	 */
	@Column(name="PAY_STEP_CODE")
	private String payStepCode;
	
	/**
	 * 직무
	 */
	@Column(name="JOB_CODE")
	private String jobCode;
		
	public CurrentAppointmentInformation(AppointmentInformation info) {
		this.apply(info);
	}
	
	void apply(AppointmentInformation info) {
		this.blngDeptCode = info.getBlngDeptCode();
		this.workDeptCode = info.getWorkDeptCode();
		this.jobGroupCode = info.getJobGroupCode();
		this.jobPositionCode = info.getJobPositionCode();
		this.occupationCode = info.getOccupationCode();
		this.jobGradeCode = info.getJobGradeCode();
		this.payStepCode = info.getPayStepCode();
		this.jobCode = info.getJobCode();
	}
}
