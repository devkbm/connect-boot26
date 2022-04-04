package com.like.hrm.staffsalary.domain.model;

import java.time.LocalDate;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMSALARYTABLE")
public class SalaryTable {
	
	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("식별자")
	@Column(name="ID", nullable = false)
	Long id;
	
	@Comment("기준일")
	@Column(name="BASE_DT")
	LocalDate baseDate;
		
	@Embedded
	SalaryAppointmentInformation appointmentInfo;
			
	@Comment("금액")
	@Column(name="AMOUNT")
	BigDecimal amount;
		
	public SalaryTable(LocalDate baseDate
					  ,SalaryAppointmentInformation appointmentInfo
					  ,BigDecimal amount) {
		this.baseDate = baseDate;
		this.appointmentInfo = appointmentInfo;		
		this.amount = amount;		
	}
		
}
