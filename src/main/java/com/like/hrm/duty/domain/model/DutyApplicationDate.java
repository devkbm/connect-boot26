package com.like.hrm.duty.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMDUTYAPPLICATIONDATE")
public class DutyApplicationDate extends AbstractAuditEntity {
	
	@EmbeddedId
	private DutyApplicationDateId id;	
	
	@Column(name="DUTY_TIME", nullable = false)
	private BigDecimal dutyTime;
	
	public DutyApplicationDate(DutyApplication dutyApplication
							  ,LocalDate date
							  ,BigDecimal dutyTime) {
		this.id = new DutyApplicationDateId(dutyApplication, date);
		this.dutyTime = dutyTime;
	}
	
}
