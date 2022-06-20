package com.like.hrm.staffsalary.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "HRMSTAFFSALARYITEM")
public class StaffSalaryItem extends AbstractAuditEntity {

	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable = false)
	Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "ID", nullable=false, updatable=false)
	StaffSalary staffSalary;
			
	@Comment("급여항목")
	@Column(name="SALARY_ITEM_CODE")
	String salaryItemCode;

	@Comment("계산일수")
	@Column(name="CALC_DAY")
	Integer calcDays;
	
	@Comment("계산식")
	@Column(name="CALC_FOMULA")
	String calcFomula;
	
	@Comment("금액")
	@Column(name="AMOUNT")
	BigDecimal amount;
		
	public StaffSalaryItem(StaffSalary staffSalary
						  ,String salaryItemCode
						  ,Integer calcDays
						  ,String calcFomula
						  ,BigDecimal amount
						  ) {
		this.staffSalary = staffSalary;
		this.salaryItemCode = salaryItemCode;
		this.calcDays = calcDays;
		this.calcFomula = calcFomula;
		this.amount = amount;
	}
}
