package com.like.hrm.staff.boundary;

import java.time.LocalDate;

import com.like.hrm.hrmcode.domain.QHrmCode;
import com.like.hrm.staff.domain.model.license.QStaffLicense;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseStaffLicense {

	String staffId;
	String staffNo;
	String staffName;
	Long seq;
	String licenseType;
	String licenseTypeName;
	String licenseNumber;
	LocalDate dateOfAcquisition;
	String certificationAuthority;
	String comment;
	
	@QueryProjection
	public ResponseStaffLicense(
			String staffId
			,String staffNo
			,String staffName
			,Long seq
			,String licenseType
			,String licenseTypeName
			,String licenseNumber
			,LocalDate dateOfAcquisition
			,String certificationAuthority
			,String comment) {
		
		this.staffId = staffId;
		this.staffNo = staffNo;
		this.staffName = staffName;
		this.seq = seq;
		this.licenseType = licenseType;
		this.licenseTypeName = licenseTypeName;
		this.licenseNumber = licenseNumber;
		this.dateOfAcquisition = dateOfAcquisition;
		this.certificationAuthority = certificationAuthority;
		this.comment = comment;
	}
	
	public static QResponseStaffLicense of(
			QStaffLicense qStaffLicense
			,QHrmCode schoolCareerType
			) {
		return new QResponseStaffLicense(
				qStaffLicense.staff.id
				,qStaffLicense.staff.staffNo
				,qStaffLicense.staff.name.name
				,qStaffLicense.id.seq				
				,qStaffLicense.licenseType
				,schoolCareerType.codeName
				,qStaffLicense.licenseNumber
				,qStaffLicense.dateOfAcquisition
				,qStaffLicense.certificationAuthority
				,qStaffLicense.comment);
	}
	
}
