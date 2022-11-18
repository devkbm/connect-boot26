package com.like.hrm.staff.domain.model.appointment;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.hrm.staff.domain.model.Staff;
import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"staff"})
@ToString(exclude = {"staff"})
@EqualsAndHashCode(callSuper = false, of = {"id"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMSTAFFAPPOINTMENTRECORD")
@EntityListeners(AuditingEntityListener.class)
public class AppointmentRecord extends AbstractAuditEntity implements Serializable {

	private static final long serialVersionUID = 3842729148497015523L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STAFF_ID", nullable=false, insertable=false, updatable=false)
	private Staff staff;
	
	@EmbeddedId
	AppointmentRecordId id;
		
	@Comment("발령일자")
	@Column(name="APPOINTMENT_DT")
	LocalDate appointmentDate;
	
	@Comment("발령종료일자")
	@Column(name="APPOINTMENT_END_DT")
	LocalDate appointmentEndDate;
	
	@Comment("발령명")
	@Column(name="RECORD_NAME")
	String recordName;

	@Comment("비고")
	@Column(name="CMT")
	String comment;
	
	@Comment("완료여부")
	@Column(name="COMPLETE_YN")
	Boolean isCompleted;		
	
	@Embedded
	AppointmentInformation info;
	
	@Builder
	public AppointmentRecord(Staff staff
							,LocalDate appointmentDate
							,LocalDate appointmentEndDate
							,String recordName
							,String comment
							,AppointmentInformation info) {
		this.staff = staff;
		this.id = new AppointmentRecordId(staff, staff.getAppointmentRecordList().getNextSequence());
		this.appointmentDate = appointmentDate;
		this.appointmentEndDate = appointmentEndDate;
		this.recordName = recordName;
		this.comment = comment;
		this.info = info;
		
		this.isCompleted = false;
	}
			
	@Builder(builderMethodName = "modifyBuilder", buildMethodName = "modify")
	public void modify(LocalDate appointmentDate
					  ,LocalDate appointmentEndDate
					  ,String recordName
					  ,String comment
					  ,AppointmentInformation info) {
		
		if (isCompleted == true) {
			throw new IllegalStateException("처리 완료된 발령은 수정할수 없습니다.");
		}
		
		this.appointmentDate = appointmentDate;
		this.appointmentEndDate = appointmentEndDate;
		this.recordName = recordName;
		this.info = info;
	}
	
	public void complete( ) {
		this.isCompleted = true;
	}
}
