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
	 * 사원번호
	 */
	@Id
	@Column(name="STAFF_ID")
	String id;	
	
	/**
	 * 한글 성명
	 */
	@Column(name="STAFF_NAME")
	String name;
	
	/**
	 * 법적 이름
	 */
	@Column(name="STAFF_NAME_LEGAL")
	String legalName;
	
	/**
	 * 영문 성명
	 */
	@Column(name="STAFF_NAME_ENG")
	String nameEng;
	
	/**
	 * 한문 성명
	 */
	@Column(name="STAFF_NAME_CHI")
	String nameChi;	
		
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
	 * 현재 발령 내용
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
	
	public static Staff of(String id, String name, String residentRegistrationNumber) {
		return new Staff(id, name, residentRegistrationNumber);
	}
	
	private Staff(String id, String name, String residentRegistrationNumber) {
		this.id = id;
		this.name = name; 
	}
	
	public Staff(String id
				,String name
				,String nameEng
				,String nameChi
				,String residentRegistrationNumber) {
		this.id = id;
		this.name = name;
		this.nameEng = nameEng;
		this.nameChi = nameChi;
		this.residentRegistrationNumber = residentRegistrationNumber;	
	}
	
	public void modifyEntity(String name
						    ,String nameEng
						    ,String nameChi
						    ,String gender
						    ,LocalDate birthday) {
		this.name 		= name;
		this.nameEng 	= nameEng;
		this.nameChi 	= nameChi;
		this.gender 	= gender;
		this.birthday 	= birthday;
	}
		
	public String getEmployeeId() {
		return this.id;
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
