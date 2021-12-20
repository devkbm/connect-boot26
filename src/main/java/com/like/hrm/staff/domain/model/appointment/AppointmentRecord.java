package com.like.hrm.staff.domain.model.appointment;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.hrm.staff.domain.model.Staff;
import com.like.system.core.domain.AuditEntity;

import lombok.AccessLevel;
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
public class AppointmentRecord extends AuditEntity implements Serializable {

	private static final long serialVersionUID = 3842729148497015523L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STAFF_ID", nullable=false, updatable=false)
	private Staff staff;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable = false)
	private Long id;
		
	@Column(name="APPOINTMENT_DT")
	private LocalDate appointmentDate;
	
	@Column(name="APPOINTMENT_END_DT")
	private LocalDate appointmentEndDate;
	
	@Column(name="RECORD_NAME")
	private String recordName;

	@Column(name="CMT")
	private String comment;
	
	@Column(name="PROC_WAIT_YN")
	private String processWatingYn;		
	
	@Embedded
	private AppointmentInformation info;
	
	public AppointmentRecord(Staff staff
							,LocalDate appointmentDate
							,LocalDate appointmentEndDate
							,String recordName
							,String comment
							,AppointmentInformation info) {
		this.staff = staff;
		this.appointmentDate = appointmentDate;
		this.appointmentEndDate = appointmentEndDate;
		this.recordName = recordName;
		this.comment = comment;
		this.info = info;
		
		this.processWatingYn = "Y";
	}
			
	public void modify(LocalDate appointmentDate
					  ,LocalDate appointmentEndDate
					  ,String recordName
					  ,String comment
					  ,AppointmentInformation info) {
		this.appointmentDate = appointmentDate;
		this.appointmentEndDate = appointmentEndDate;
		this.recordName = recordName;
		this.info = info;
	}
}
