package com.like.hrm.staff.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.hrmcode.domain.QHrmCode;
import com.like.hrm.staff.boundary.StaffDTO.SearchStaff;
import com.like.hrm.staff.boundary.ResponseStaffAppointmentRecord;
import com.like.hrm.staff.boundary.ResponseStaffCurrentAppointment;
import com.like.hrm.staff.boundary.ResponseStaffDutyResponsibility;
import com.like.hrm.staff.boundary.ResponseStaffFamily;
import com.like.hrm.staff.boundary.ResponseStaffLicense;
import com.like.hrm.staff.boundary.ResponseStaffSchoolCareer;
import com.like.hrm.staff.domain.model.QStaff;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffQueryRepository;
import com.like.hrm.staff.domain.model.appointment.QAppointmentRecord;
import com.like.hrm.staff.domain.model.dutyresponsibility.QStaffDuty;
import com.like.hrm.staff.domain.model.family.QStaffFamily;
import com.like.hrm.staff.domain.model.license.QStaffLicense;
import com.like.hrm.staff.domain.model.schoolcareer.QStaffSchoolCareer;
import com.like.system.dept.domain.QDept;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Repository
public class StaffQuery implements StaffQueryRepository {

	private JPAQueryFactory query;	
	private final QStaff qStaff = QStaff.staff;
	private final QAppointmentRecord qAppointmentRecord = QAppointmentRecord.appointmentRecord;
	private final QStaffDuty qStaffDuty = QStaffDuty.staffDuty;
	private final QStaffFamily qStaffFamily = QStaffFamily.staffFamily;
	private final QStaffSchoolCareer qStaffSchoolCareer = QStaffSchoolCareer.staffSchoolCareer;
	private final QStaffLicense qStaffLicense = QStaffLicense.staffLicense;
	
	public StaffQuery(JPAQueryFactory queryFactory) {
		this.query = queryFactory;
	}
	
	@Override
	public List<Staff> getStaffList(SearchStaff dto) {
		return query.selectFrom(qStaff).fetch();
	}
	
	@Override
	public List<ResponseStaffAppointmentRecord> getStaffAppointmentRecordList(String staffId) {		
		
		QDept blngDeptCode = QDept.dept;
		QDept workDeptCode = new QDept("workDeptCode");
		
		QHrmCode appointmentTypeCode = new QHrmCode("appointmentTypeCode");
		QHrmCode jobGroupCode = QHrmCode.hrmCode;
		QHrmCode jobPositionCode = new QHrmCode("jobPositionCode");
		QHrmCode occupationCode = new QHrmCode("occupationCode");
		QHrmCode jobGradeCode = new QHrmCode("jobGradeCode");
		QHrmCode payStepCode = new QHrmCode("payStepCode");
		QHrmCode jobCode = new QHrmCode("jobCode");				
		QHrmCode dutyResponsibilityCode = new QHrmCode("dutyResponsibilityCode");
		
		return query.select(ResponseStaffAppointmentRecord.of(qStaff
															 ,qAppointmentRecord											  
															 ,blngDeptCode
															 ,workDeptCode
															 ,appointmentTypeCode 
															 ,jobGroupCode
															 ,jobPositionCode
															 ,occupationCode
															 ,jobGradeCode
															 ,payStepCode
															 ,jobCode
															 ,dutyResponsibilityCode))
				   .from(qStaff)
				   .join(qAppointmentRecord)
				   		.on(qStaff.id.eq(qAppointmentRecord.staff.id))
				   .leftJoin(blngDeptCode)
				   		.on(blngDeptCode.organizationCode.eq(qStaff.organizationCode)
				   		.and(blngDeptCode.deptCode.eq(qAppointmentRecord.info.blngDeptCode)))
			   	   .leftJoin(workDeptCode)
			   			.on(workDeptCode.organizationCode.eq(qStaff.organizationCode)
			   			.and(workDeptCode.deptCode.eq(qAppointmentRecord.info.workDeptCode)))
			   	   .leftJoin(appointmentTypeCode)
				   		.on(appointmentTypeCode.id.typeId.eq("HR0000")
				   		.and(qAppointmentRecord.appointmentTypeCode.eq(appointmentTypeCode.id.code)))
				   .leftJoin(jobGroupCode)
				   		.on(jobGroupCode.id.typeId.eq("HR0001")
				   		.and(qAppointmentRecord.info.jobGroupCode.eq(jobGroupCode.id.code)))
				   .leftJoin(jobPositionCode)
				   		.on(jobPositionCode.id.typeId.eq("HR0002")
				   		.and(qAppointmentRecord.info.jobPositionCode.eq(jobPositionCode.id.code)))
				   .leftJoin(occupationCode)
				   		.on(occupationCode.id.typeId.eq("HR0003")
				   		.and(qAppointmentRecord.info.occupationCode.eq(occupationCode.id.code)))
				   .leftJoin(jobGradeCode)
				   		.on(jobGradeCode.id.typeId.eq("HR0004")
				   		.and(qAppointmentRecord.info.jobGradeCode.eq(jobGradeCode.id.code)))
				   .leftJoin(payStepCode)
				   		.on(payStepCode.id.typeId.eq("HR0005")
				   		.and(qAppointmentRecord.info.payStepCode.eq(payStepCode.id.code)))
				   .leftJoin(jobCode)
				   		.on(jobCode.id.typeId.eq("HR0006")
				   		.and(qAppointmentRecord.info.jobCode.eq(jobCode.id.code)))
				   .leftJoin(dutyResponsibilityCode)
				   		.on(dutyResponsibilityCode.id.typeId.eq("HR0007")
				   		.and(qAppointmentRecord.info.dutyResponsibilityCode.eq(dutyResponsibilityCode.id.code)))
				   .where(qStaff.id.eq(staffId))
				   .fetch();
	}	
	
	@Override
	public ResponseStaffCurrentAppointment getStaffCurrentAppointment(String staffId) {

		QDept blngDeptCode = QDept.dept;
		QDept workDeptCode = new QDept("workDeptCode");
				
		QHrmCode jobGroupCode = QHrmCode.hrmCode;
		QHrmCode jobPositionCode = new QHrmCode("jobPositionCode");
		QHrmCode occupationCode = new QHrmCode("occupationCode");
		QHrmCode jobGradeCode = new QHrmCode("jobGradeCode");
		QHrmCode payStepCode = new QHrmCode("payStepCode");
		QHrmCode jobCode = new QHrmCode("jobCode");						
		
		return query.select(ResponseStaffCurrentAppointment.of(qStaff
														 ,blngDeptCode
														 ,workDeptCode
														 ,jobGroupCode
														 ,jobPositionCode
														 ,occupationCode
														 ,jobGradeCode
														 ,payStepCode
														 ,jobCode))
					.from(qStaff)				
					.leftJoin(blngDeptCode)
						.on(blngDeptCode.organizationCode.eq(qStaff.organizationCode)
			   			.and(blngDeptCode.deptCode.eq(qStaff.currentAppointment.blngDeptCode)))
					.leftJoin(workDeptCode)
			   			.on(workDeptCode.organizationCode.eq(qStaff.organizationCode)
			   			.and(workDeptCode.deptCode.eq(qStaff.currentAppointment.workDeptCode)))			   		
			   		.leftJoin(jobGroupCode)
				   		.on(jobGroupCode.id.typeId.eq("HR0001")
				   		.and(qStaff.currentAppointment.jobGroupCode.eq(jobGroupCode.id.code)))
				   	.leftJoin(jobPositionCode)
				   		.on(jobPositionCode.id.typeId.eq("HR0002")
				   		.and(qStaff.currentAppointment.jobPositionCode.eq(jobPositionCode.id.code)))
				   	.leftJoin(occupationCode)
				   		.on(occupationCode.id.typeId.eq("HR0003")
				   		.and(qStaff.currentAppointment.occupationCode.eq(occupationCode.id.code)))
				   	.leftJoin(jobGradeCode)
				   		.on(jobGradeCode.id.typeId.eq("HR0004")
				   		.and(qStaff.currentAppointment.jobGradeCode.eq(jobGradeCode.id.code)))
				   	.leftJoin(payStepCode)
				   		.on(payStepCode.id.typeId.eq("HR0005")
				   		.and(qStaff.currentAppointment.payStepCode.eq(payStepCode.id.code)))
				   	.leftJoin(jobCode)
				   		.on(jobCode.id.typeId.eq("HR0006")
				   		.and(qStaff.currentAppointment.jobCode.eq(jobCode.id.code)))				   
				   	.where(qStaff.id.eq(staffId))
				   	.fetchFirst();
	}	
	
	@Override
	public List<ResponseStaffDutyResponsibility> getStaffDutyResponsibility(String staffId) {
		QHrmCode dutyResponsibilityCode = new QHrmCode("dutyResponsibilityCode");
		
		return query.select(ResponseStaffDutyResponsibility.of(qStaff, qStaffDuty, dutyResponsibilityCode))
				   	.from(qStaff)
				   	.join(qStaffDuty)
			   			.on(qStaff.id.eq(qStaffDuty.staff.id))
			   		.leftJoin(dutyResponsibilityCode)
			   	   		.on(dutyResponsibilityCode.id.typeId.eq("HR0007")
			   	   		.and(qStaffDuty.dutyResponsibilityCode.eq(dutyResponsibilityCode.id.code)))
			   	   	.fetch();
	}
			
	@Override
	public List<ResponseStaffFamily> getStaffFamily(String staffId) {
	
		QHrmCode familyRelationCode = QHrmCode.hrmCode;
		
		return query.select(ResponseStaffFamily.of(qStaffFamily, familyRelationCode))
				    .from(qStaff)
				    .join(qStaffFamily)
				    	.on(qStaff.id.eq(qStaffFamily.staff.id))
				   	.leftJoin(familyRelationCode)
			   	   		.on(familyRelationCode.id.typeId.eq("HR0008")
			   	   		.and(qStaffFamily.relation.eq(familyRelationCode.id.code)))
			   	   	.fetch();
	}

	@Override
	public List<ResponseStaffSchoolCareer> getStaffSchoolCareer(String staffId) {
		
		QHrmCode schoolCareerType = new QHrmCode("schoolCareerType");
		QHrmCode schoolCode = new QHrmCode("schoolCode");
		
		return query.select(ResponseStaffSchoolCareer.of(qStaffSchoolCareer, schoolCareerType, schoolCode))
			    	.from(qStaff)
			    	.join(qStaffSchoolCareer)
			    		.on(qStaff.id.eq(qStaffSchoolCareer.staff.id))
			    	.leftJoin(schoolCareerType)
		   	   			.on(schoolCareerType.id.typeId.eq("HR0009")
		   	   			.and(qStaffSchoolCareer.schoolCareerType.eq(schoolCareerType.id.code)))
		   	   		.leftJoin(schoolCode)
	   	   				.on(schoolCode.id.typeId.eq("HR0010")
	   	   				.and(qStaffSchoolCareer.schoolCode.eq(schoolCode.id.code)))
	   	   			.fetch();
	}

	@Override
	public List<ResponseStaffLicense> getStaffLicense(String staffId) {
		QHrmCode licenseType = new QHrmCode("licenseType");
				
		return query.select(ResponseStaffLicense.of(qStaffLicense, licenseType))
		    	.from(qStaff)
		    	.join(qStaffLicense)
		    		.on(qStaff.id.eq(qStaffLicense.staff.id))
		    	.leftJoin(licenseType)
	   	   			.on(licenseType.id.typeId.eq("HR0011")
	   	   			.and(qStaffLicense.licenseType.eq(licenseType.id.code)))	   	   		
   	   			.fetch();
	}
	
}
