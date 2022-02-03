package com.like.hrm.staff.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.hrmtypecode.domain.QHrmTypeDetailCode;
import com.like.hrm.staff.boundary.QResponseStaffAppointmentRecord;
import com.like.hrm.staff.boundary.StaffDTO.SearchStaff;
import com.like.hrm.staff.boundary.ResponseStaffAppointmentRecord;
import com.like.hrm.staff.domain.model.QStaff;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffQueryRepository;
import com.like.hrm.staff.domain.model.appointment.QAppointmentRecord;
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ResponseStaffAppointmentRecord> getStaffAppointmentRecordList(String staffId) {		
		
		QHrmTypeDetailCode jobGroupCode = QHrmTypeDetailCode.hrmTypeDetailCode;
		QHrmTypeDetailCode jobPositionCode = new QHrmTypeDetailCode("jobPositionCode");
		QHrmTypeDetailCode occupationCode = new QHrmTypeDetailCode("occupationCode");
		QHrmTypeDetailCode jobGradeCode = new QHrmTypeDetailCode("jobGradeCode");
		QHrmTypeDetailCode payStepCode = new QHrmTypeDetailCode("payStepCode");
		QHrmTypeDetailCode jobCode = new QHrmTypeDetailCode("jobCode");				
		QHrmTypeDetailCode dutyResponsibilityCode = new QHrmTypeDetailCode("dutyResponsibilityCode");
		
		return queryFactory.select(projections(qStaff, qAppointmentRecord, jobGroupCode, jobPositionCode, occupationCode, jobGradeCode, payStepCode, jobCode, dutyResponsibilityCode))
						   .from(qStaff)
						   .join(qAppointmentRecord)
						   		.on(qStaff.id.eq(qAppointmentRecord.staff.id))
						   .leftJoin(jobGroupCode)
						   		.on(jobGroupCode.id.codeType.eq("HR0001")
						   		.and(qAppointmentRecord.info.jobGroupCode.eq(jobGroupCode.id.code)))
						   .leftJoin(jobPositionCode)
						   		.on(jobPositionCode.id.codeType.eq("HR0002")
						   		.and(qAppointmentRecord.info.jobPositionCode.eq(jobPositionCode.id.code)))
						   .leftJoin(occupationCode)
						   		.on(occupationCode.id.codeType.eq("HR0003")
						   		.and(qAppointmentRecord.info.occupationCode.eq(occupationCode.id.code)))
						   .leftJoin(jobGradeCode)
						   		.on(jobGradeCode.id.codeType.eq("HR0004")
						   		.and(qAppointmentRecord.info.jobGradeCode.eq(jobGradeCode.id.code)))
						   .leftJoin(payStepCode)
						   		.on(payStepCode.id.codeType.eq("HR0005")
						   		.and(qAppointmentRecord.info.payStepCode.eq(payStepCode.id.code)))
						   .leftJoin(jobCode)
						   		.on(jobCode.id.codeType.eq("HR0006")
						   		.and(qAppointmentRecord.info.jobCode.eq(jobCode.id.code)))
						   .leftJoin(dutyResponsibilityCode)
						   		.on(dutyResponsibilityCode.id.codeType.eq("HR0007")
						   		.and(qAppointmentRecord.info.dutyResponsibilityCode.eq(dutyResponsibilityCode.id.code)))
						   .where(qStaff.id.eq(staffId))
						   .fetch();
	}
	
	private QResponseStaffAppointmentRecord projections(QStaff qStaff
											, QAppointmentRecord qRecord
											, QHrmTypeDetailCode jobGroupCode
											, QHrmTypeDetailCode jobPositionCode
											, QHrmTypeDetailCode occupationCode
											, QHrmTypeDetailCode jobGradeCode
											, QHrmTypeDetailCode payStepCode
											, QHrmTypeDetailCode jobCode
											, QHrmTypeDetailCode dutyResponsibilityCode) {
		
		return new QResponseStaffAppointmentRecord(QStaff.staff.id
											   ,qRecord.id
											   ,qRecord.appointmentDate
											   ,qRecord.appointmentEndDate
											   ,qRecord.recordName
											   ,qRecord.comment
											   ,qRecord.processWatingYn
											   ,qRecord.info.blngDeptCode
											   ,qRecord.info.blngDeptCode
											   ,qRecord.info.workDeptCode
											   ,qRecord.info.workDeptCode
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

}
