package com.like.hrm.staff.boundary;

import com.like.hrm.hrmcode.domain.QHrmCode;
import com.like.hrm.staff.domain.model.QStaff;
import com.like.system.dept.domain.QDept;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseStaffCard {

	String staffId;	
	String staffNo;
	String staffName;
	String profilePicture;
	String extensionNumber;
	String mobileNumber;
	String blngDeptName;
	String workDeptName;
	String jobPositionName;
	
	@QueryProjection
	public ResponseStaffCard(String staffId
							,String staffNo
							,String staffName
							,String profilePicture
							,String extensionNumber
							,String mobileNumber
							,String blngDeptName
							,String workDeptName
							,String jobPositionName) {	
		this.staffId = staffId;
		this.staffNo = staffNo;
		this.staffName = staffName;
		this.profilePicture = profilePicture;
		this.extensionNumber = extensionNumber;
		this.mobileNumber = mobileNumber;
		this.blngDeptName = blngDeptName;
		this.workDeptName = workDeptName;
		this.jobPositionName = jobPositionName;
	}
	
	public static QResponseStaffCard of(
			QStaff qStaff			
			,QDept blngDept
			,QDept workDept
			,QHrmCode jobPositionCode) {
		return new QResponseStaffCard(qStaff.id
									 ,qStaff.staffNo
									 ,qStaff.name.name
									 ,qStaff.imagePath
									 ,qStaff.contact.extensionNumber.number
									 ,qStaff.contact.mobileNumber.number
									 ,blngDept.deptNameKorean
									 ,workDept.deptNameKorean
									 ,jobPositionCode.codeName);		
	}

	
}
