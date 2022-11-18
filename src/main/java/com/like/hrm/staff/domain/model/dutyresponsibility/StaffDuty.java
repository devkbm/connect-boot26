package com.like.hrm.staff.domain.model.dutyresponsibility;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.hrm.staff.domain.model.Staff;
import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMSTAFFDUTYRESPONSIBILITY")
@EntityListeners(AuditingEntityListener.class)
public class StaffDuty extends AbstractAuditEntity implements Serializable {

	private static final long serialVersionUID = -5607018550367077054L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STAFF_ID", nullable=false, updatable=false, insertable = false)
	private Staff staff;
	
	@EmbeddedId	
	StaffDutyId id;
				
	@Comment("직책코드")
	@Column(name="DUTY_RESPONSIBILITY_CODE")
	String dutyResponsibilityCode;
		
	@Comment("시작일자")
	@Column(name="FROM_DT")
	LocalDate fromDate;
	
	@Comment("종료일자")
	@Column(name="TO_DT")
	LocalDate toDate;
					
	@Comment("급여적용여부")
	@Column(name="PAY_APPLY_YN")
	Boolean isPayApply;	
	
	@Builder
	public StaffDuty(Staff staff, String dutyResponsibilityCode, LocalDate fromDate, LocalDate toDate, Boolean isPayApply) {
		this.staff = staff;		
		this.id = new StaffDutyId(staff, staff.getStaffDutyResponsibilityList().getNextSeq());
		this.dutyResponsibilityCode = dutyResponsibilityCode;	
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.isPayApply = isPayApply;
		
		staff.getStaffDutyResponsibilityList().add(this);
	}
	
	@Builder(builderMethodName = "modifyBuilder", buildMethodName = "modify")
	public void modifyEntity(String dutyResponsibilityCode
							,LocalDate fromDate
							,LocalDate toDate			
							,Boolean isPayApply) {
		this.dutyResponsibilityCode = dutyResponsibilityCode;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.isPayApply = isPayApply;
	}
}
