package com.like.hrm.staff.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecordList;
import com.like.hrm.staff.domain.model.family.FamilyList;
import com.like.hrm.staff.domain.model.license.LicenseList;
import com.like.hrm.staff.domain.model.schoolcareer.SchoolCareerList;
import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Aggregate Root 
 * <p> 직원정보 </p>
 *  
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMSTAFF")
@EntityListeners(AuditingEntityListener.class)
public class Staff extends AbstractAuditEntity implements Serializable {

	private static final long serialVersionUID = -3164713918774455925L;
	
	@Id
	@Comment("직원ID")
	@Column(name="STAFF_ID")
	String id;
	
	@Column(name="ORG_CD")
	String organizationCode;
	
	@Column(name="STAFF_NO")
	String staffNo;
		
	@Embedded
	StaffName name;
				
	@Embedded
	ResidentRegistrationNumber residentRegistrationNumber;
		
	@Comment("성별")
	@Column(name="GENDER")
	String gender;
				
	@Comment("생일")
	@Column(name="BIRTHDAY")
	LocalDate birthday;	
		
	@Comment("근무상태")
	@Column(name="WORK_STATE_CODE")
	String workStateCode;
		
	@Comment("이미지경로")
	@Column(name="IMG_PATH")
	String imagePath;
		
	@Embedded
	CurrentAppointmentInformation currentAppointment;
		
	@Embedded
	AppointmentRecordList appointmentRecordList;
		
	@Embedded
	FamilyList familyList;
		
	@Embedded
	SchoolCareerList schoolCareerList;
	
	/**
	 * 자격면허 명단
	 */
	@Embedded
	LicenseList licenseList;			
			
	public Staff(String organizationCode, StaffNoCreateStrategy strategy, StaffName name, String residentRegistrationNumber) {
		this.id 						= organizationCode + "_" + strategy.create();
		this.organizationCode 			= organizationCode;
		this.staffNo					= strategy.create();
		this.name 						= name; 
		this.residentRegistrationNumber = ResidentRegistrationNumber.of(residentRegistrationNumber);
		this.gender 					= this.residentRegistrationNumber.getGender();
		this.birthday 					= this.residentRegistrationNumber.getBirthDay();		
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
