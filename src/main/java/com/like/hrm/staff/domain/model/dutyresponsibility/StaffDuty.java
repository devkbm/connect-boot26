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
import com.like.system.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMSTAFFDUTYRESPONSIBILITY")
@EntityListeners(AuditingEntityListener.class)
public class StaffDuty extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -5607018550367077054L;
	
	@EmbeddedId	
	StaffDutyId id;
				
	@Comment("종료일자")
	@Column(name="TO_DT")
	LocalDate toDate;
		
	@Comment("대리여부")
	@Column(name="DEPUTY_YN")
	String deputyYn;
		
	@Comment("급여여부")
	@Column(name="PAY_YN")
	String payYn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STAFF_ID", nullable=false, updatable=false, insertable = false)
	private Staff staff;
	
	public StaffDuty(Staff staff, StaffDutyId id, LocalDate toDate) {
		this.staff = staff;
		this.id = id;
		this.toDate = toDate;
	}
	
	public void modifyEntity(LocalDate toDate
							,String deputyYn
							,String payYn) {
		this.toDate = toDate;
		this.deputyYn = deputyYn;
		this.payYn = payYn;
	}
}
