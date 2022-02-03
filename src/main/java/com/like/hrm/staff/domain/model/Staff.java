package com.like.hrm.staff.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecordList;
import com.like.hrm.staff.domain.model.family.FamilyList;
import com.like.hrm.staff.domain.model.license.LicenseList;
import com.like.hrm.staff.domain.model.schoolcareer.SchoolCareerList;
import com.like.system.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Aggregate Root
 * 
 * <p> 직원정보 </p>
 * 
 * @author CB457
 *
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMSTAFF")
@EntityListeners(AuditingEntityListener.class)
public class Staff extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -3164713918774455925L;

	/**
	 * 직원번호
	 */
	@Id
	@Column(name="STAFF_ID")
	String id;	
	
	/**
	 * 직원성명
	 */
	@Embedded
	StaffName name;
			
	/**
	 * 주민번호
	 */
	@Column(name="RREGNO")
	String residentRegistrationNumber;
	
	/**
	 * 성별
	 */
	@Column(name="GENDER")
	String gender;
			
	/**
	 * 생일
	 */
	@Column(name="BIRTHDAY")
	LocalDate birthday;	
	
	/**
	 * 근무상태
	 */
	@Column(name="WORK_STATE_CODE")
	String workStateCode;
	
	/**
	 * 이미지경로
	 */
	@Column(name="IMG_PATH")
	String imagePath;
	
	/**
	 * 현재 발령 정보
	 */
	@Embedded
	CurrentAppointmentInformation currentAppointment;
	
	/**
	 * 발령기록 명단
	 */
	@Embedded
	AppointmentRecordList appointmentRecordList;
	
	/**
	 * 가족 명단
	 */
	@Embedded
	FamilyList familyList;
	
	/**
	 * 학력이력
	 */
	@Embedded
	SchoolCareerList schoolCareerList;
	
	/**
	 * 자격면허 명단
	 */
	@Embedded
	LicenseList licenseList;			
	
	public static Staff of(String id, StaffName name, String residentRegistrationNumber) {
		return new Staff(id, name, residentRegistrationNumber);
	}
	
	private Staff(String id, StaffName name, String residentRegistrationNumber) {
		this.id = id;
		this.name = name; 
	}
			
	public void modifyEntity(StaffName name
						    ,LocalDate birthday) {
		this.name 		= name;				
		this.birthday 	= birthday;
	}
					
	public void changeImagePath(String imagePath) {
		this.imagePath = imagePath;
	}	
	
	public void applyAppointmentRecord(Long appointmentRecordId) {		
		AppointmentRecord record = this.appointmentRecordList.get(appointmentRecordId);
		
		if (this.currentAppointment == null) this.currentAppointment = new CurrentAppointmentInformation(record.getInfo()); 		
		
		this.currentAppointment.apply(record.getInfo());
	}

}
