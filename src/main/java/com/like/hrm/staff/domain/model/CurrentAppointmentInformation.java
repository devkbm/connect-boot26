package com.like.hrm.staff.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Comment;

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
