package com.like.hrm.staffsalary.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Comment;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMSTAFFSALARY")
public class StaffSalary extends AbstractAuditEntity implements Serializable {

	private static final long serialVersionUID = -4083229804219573611L;

	@Id
	@Comment("실별자")
	@Column(name="ID")
	String id;
	
	@Comment("직원번호")
	@Column(name="STAFF_ID")
	String staffId;
		
	@Comment("지급일")
	@Column(name="PAY_DT")
	LocalDate payDate;
	
	@Comment("급여구분")
	@Column(name="SALARY_TYPE")
	String salaryType;
	
	// 급여계산 기준 인사정보
	@Embedded
	SalaryAppointmentInformation appointmentInfo;
	
	// 급여항목
	@Transient
	List<StaffSalaryItem> items;	
	
}
