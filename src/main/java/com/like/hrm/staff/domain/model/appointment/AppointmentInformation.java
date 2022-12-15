package com.like.hrm.staff.domain.model.appointment;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//용어 참고 https://terms.naver.com/list.naver?cid=51072&categoryId=51072
@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class AppointmentInformation {

	@Comment("소속부서코드")
	@Column(name="BLNG_DEPT_ID")
	private String blngDeptId;
		
	@Comment("근무부서코드")
	@Column(name="WORK_DEPT_ID")
	private String workDeptId;
		
	@Column(name="JOB_GROUP_CODE")
	private String jobGroupCode;
		
	@Comment("직위코드")
	@Column(name="JOB_POSITION_CODE")
	private String jobPositionCode;
		
	@Comment("직종코드")
	@Column(name="OCCUPATION_CODE")
	private String occupationCode;
		
	@Comment("직급코드")
	@Column(name="JOB_GRADE_CODE")
	private String jobGradeCode;
		
	@Comment("호봉코드")
	@Column(name="PAY_STEP_CODE")
	private String payStepCode;
		
	@Comment("직무코드")
	@Column(name="JOB_CODE")
	private String jobCode;
		
	@Comment("직책코드")
	@Column(name="DUTY_RESPONSIBILITY_CODE")
	private String dutyResponsibilityCode;
}
