package com.like.hrm.staff.boundary;

import com.like.hrm.hrmcode.domain.QHrmCode;
import com.like.hrm.staff.domain.model.family.QStaffFamily;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseStaffFamily {
	
	String organizationCode;			
	String staffId;
	String staffNo;
	String staffName;
	Long seq;
	String familyName;
	String familyRRN;
	String familyRelationCode;
	String familyRelationName;
	String occupation;
	String schoolCareerType;
	String comment;
	
	@QueryProjection
	public ResponseStaffFamily(String organizationCode
							  ,String staffId
							  ,String staffNo
							  ,String staffName
							  ,Long seq
							  ,String familyName
							  ,String familyRRN
							  ,String familyRelationCode
							  ,String familyRelationName
							  ,String occupation
							  ,String schoolCareerType
							  ,String comment) {	
		this.organizationCode = organizationCode;
		this.staffId = staffId;
		this.staffNo = staffNo;
		this.staffName = staffName;
		this.seq = seq;
		this.familyName = familyName;
		this.familyRRN = familyRRN;
		this.familyRelationCode = familyRelationCode;
		this.familyRelationName = familyRelationName;
		this.occupation = occupation;
		this.schoolCareerType = schoolCareerType;
		this.comment = comment;
	}	
	
	public static QResponseStaffFamily of(QStaffFamily qStaffFamily, QHrmCode familyRelationCode) {
		return new QResponseStaffFamily(
				qStaffFamily.staff.organizationCode
				,qStaffFamily.id.staffId
				,qStaffFamily.staff.staffNo
				,qStaffFamily.staff.name.name
				,qStaffFamily.id.seq
				,qStaffFamily.name
				,qStaffFamily.residentRegistrationNumber
				,qStaffFamily.relation
				,familyRelationCode.codeName
				,qStaffFamily.occupation													
				,qStaffFamily.schoolCareerType
				,qStaffFamily.comment);
	}
	
}
