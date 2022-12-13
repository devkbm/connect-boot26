package com.like.hrm.staff.boundary;

import java.time.LocalDate;

import com.like.hrm.hrmcode.domain.QHrmCode;
import com.like.hrm.staff.domain.model.schoolcareer.QStaffSchoolCareer;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseStaffSchoolCareer {

	String staffId;
	String staffNo;
	String staffName;
	Long seq;
	String schoolCareerType;
	String schoolCareerTypeName;
	String schoolCode;
	String schoolCodeName;
	LocalDate fromDate;
	LocalDate toDate;
	String majorName;
	String pluralMajorName;
	String location;
	Integer lessonYear;
	String comment;
	
	@QueryProjection
	public ResponseStaffSchoolCareer(String staffId
									,String staffNo
									,String staffName
									,Long seq
									,String schoolCareerType
									,String schoolCareerTypeName
									,String schoolCode
									,String schoolCodeName
									,LocalDate fromDate
									,LocalDate toDate
									,String majorName
									,String pluralMajorName
									,String location
									,Integer lessonYear
									,String comment) {		
		this.staffId = staffId;
		this.staffNo = staffNo;
		this.staffName = staffName;
		this.seq = seq;
		this.schoolCareerType = schoolCareerType;
		this.schoolCareerTypeName = schoolCareerTypeName;
		this.schoolCode = schoolCode;
		this.schoolCodeName = schoolCodeName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.majorName = majorName;
		this.pluralMajorName = pluralMajorName;
		this.location = location;
		this.lessonYear = lessonYear;
		this.comment = comment;
	}
	
	
	public static QResponseStaffSchoolCareer of(
			QStaffSchoolCareer qSchoolCareer
			,QHrmCode schoolCareerType
			,QHrmCode schoolCode) {
		return new QResponseStaffSchoolCareer(qSchoolCareer.staff.id
									,qSchoolCareer.staff.staffNo
									,qSchoolCareer.staff.name.name
									,qSchoolCareer.id.seq
									,qSchoolCareer.schoolCareerType
									,schoolCareerType.codeName
									,qSchoolCareer.schoolCode
									,schoolCode.codeName
									,qSchoolCareer.period.from
									,qSchoolCareer.period.to
									,qSchoolCareer.majorName
									,qSchoolCareer.pluralMajorName
									,qSchoolCareer.location
									,qSchoolCareer.lessonYear
									,qSchoolCareer.comment);
	}
}
