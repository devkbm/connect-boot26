package com.like.hrm.staff.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.hrmcode.domain.QHrmCode;
import com.like.hrm.staff.boundary.QResponseStaffAppointmentRecord;
import com.like.hrm.staff.boundary.QResponseStaffCurrentAppointment;
import com.like.hrm.staff.boundary.StaffDTO.SearchStaff;
import com.like.hrm.staff.boundary.ResponseStaffAppointmentRecord;
import com.like.hrm.staff.boundary.ResponseStaffCurrentAppointment;
import com.like.hrm.staff.domain.model.QStaff;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffQueryRepository;
import com.like.hrm.staff.domain.model.appointment.QAppointmentRecord;
import com.like.system.dept.domain.QDept;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Repository
public class StaffQuery implements StaffQueryRepository {

	private JPAQueryFactory queryFactory;	
	private final QStaff qStaff = QStaff.staff;
	private final QAppointmentRecord qAppointmentRecord = QAppointmentRecord.appointmentRecord;	
	
	public StaffQuery(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<Staff> getStaffList(SearchStaff dto) {
		return queryFactory.selectFrom(qStaff).fetch();
	}
	
	@Override
	public List<ResponseStaffAppointmentRecord> getStaffAppointmentRecordList(String staffId) {		
		
		QDept blngDeptCode = QDept.dept;
		QDept workDeptCode = new QDept("workDeptCode");
		
		QHrmCode jobGroupCode = QHrmCode.hrmCode;
		QHrmCode jobPositionCode = new QHrmCode("jobPositionCode");
		QHrmCode occupationCode = new QHrmCode("occupationCode");
		QHrmCode jobGradeCode = new QHrmCode("jobGradeCode");
		QHrmCode payStepCode = new QHrmCode("payStepCode");
		QHrmCode jobCode = new QHrmCode("jobCode");				
		QHrmCode dutyResponsibilityCode = new QHrmCode("dutyResponsibilityCode");
		
		return queryFactory.select(projections(qStaff
											  ,qAppointmentRecord
											  ,blngDeptCode
											  ,workDeptCode
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
		
		return queryFactory.select(projection(qStaff
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
	
	private QResponseStaffAppointmentRecord projections(QStaff qStaff								
											, QAppointmentRecord qRecord
											, QDept blngDeptCode
											, QDept workDeptCode
											, QHrmCode jobGroupCode
											, QHrmCode jobPositionCode
											, QHrmCode occupationCode
											, QHrmCode jobGradeCode
											, QHrmCode payStepCode
											, QHrmCode jobCode
											, QHrmCode dutyResponsibilityCode) {
		
		return new QResponseStaffAppointmentRecord(QStaff.staff.id
											   ,qRecord.id
											   ,qRecord.appointmentDate
											   ,qRecord.appointmentEndDate
											   ,qRecord.recordName
											   ,qRecord.comment
											   ,qRecord.processWatingYn
											   ,qRecord.info.blngDeptCode
											   ,blngDeptCode.deptNameKorean
											   ,qRecord.info.workDeptCode
											   ,workDeptCode.deptNameKorean
											   ,qRecord.info.jobGroupCode
											   ,jobGroupCode.codeName
											   ,qRecord.info.jobPositionCode
											   ,jobPositionCode.codeName
											   ,qRecord.info.occupationCode
											   ,occupationCode.codeName
											   ,qRecord.info.jobGradeCode
											   ,jobGradeCode.codeName
											   ,qRecord.info.payStepCode
											   ,payStepCode.codeName
											   ,qRecord.info.jobCode
											   ,jobCode.codeName
											   ,qRecord.info.dutyResponsibilityCode
											   ,dutyResponsibilityCode.codeName);
	}

	
	private QResponseStaffCurrentAppointment projection(QStaff qStaff			
													   ,QDept blngDeptCode
													   ,QDept workDeptCode
													   ,QHrmCode jobGroupCode
													   ,QHrmCode jobPositionCode
													   ,QHrmCode occupationCode
													   ,QHrmCode jobGradeCode
													   ,QHrmCode payStepCode
													   ,QHrmCode jobCode) {
		return new QResponseStaffCurrentAppointment(qStaff.id
												   ,qStaff.organizationCode
												   ,qStaff.staffNo
												   ,qStaff.currentAppointment.blngDeptCode
												   ,blngDeptCode.deptNameKorean
												   ,qStaff.currentAppointment.workDeptCode
												   ,workDeptCode.deptNameKorean
												   ,qStaff.currentAppointment.jobGroupCode
												   ,jobGroupCode.codeName
												   ,qStaff.currentAppointment.jobPositionCode
												   ,jobPositionCode.codeName
												   ,qStaff.currentAppointment.occupationCode
												   ,occupationCode.codeName
												   ,qStaff.currentAppointment.jobGradeCode
												   ,jobGradeCode.codeName
												   ,qStaff.currentAppointment.payStepCode
												   ,payStepCode.codeName
												   ,qStaff.currentAppointment.jobCode
												   ,jobCode.codeName);
	}
	

}
