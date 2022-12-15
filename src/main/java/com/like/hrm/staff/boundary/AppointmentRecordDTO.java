package com.like.hrm.staff.boundary;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.appointment.AppointmentInformation;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;

import lombok.AccessLevel;
import lombok.Builder;

public class AppointmentRecordDTO {
	
	@Builder(access = AccessLevel.PRIVATE)
	public static record FormStaffAppointmentRecord(
			String clientAppUrl,
			String organizationCode,
			@NotEmpty
			String staffId,
			String staffNo,
			String staffName,			
			Long seq,
			String appointmentTypeCode,
			LocalDate appointmentDate,
			LocalDate appointmentEndDate,			
			String recordName,
			String comment,				
			Boolean isCompleted,
			String blngDeptId,			
			String workDeptId,			
			String jobGroupCode,			
			String jobPositionCode,			
			String occupationCode,			
			String jobGradeCode,
			String payStepCode,			
			String jobCode,			
			String dutyResponsibilityCode
			) {
		
		public AppointmentRecord newEntity(Staff staff) {		
			AppointmentInformation info = newAppointmentInformation();
			
			return new AppointmentRecord(staff, appointmentTypeCode, appointmentDate, appointmentEndDate, recordName, comment, info);				
		}
		
		public void modifyEntity(AppointmentRecord entity) {
			
			AppointmentInformation info = newAppointmentInformation();
			
			entity.modify(appointmentTypeCode
					     ,appointmentDate
						 ,appointmentEndDate
						 ,recordName
						 ,comment
						 ,info);									
		}
				
		public static FormStaffAppointmentRecord convert(AppointmentRecord entity) {
			if (entity == null) return null;																													

			Optional<AppointmentInformation> info = Optional.ofNullable(entity.getInfo());
			
			return FormStaffAppointmentRecord.builder()
											 .staffId(entity.getStaff().getId())
											 .staffNo(entity.getStaff().getStaffNo())
											 .staffName(entity.getStaff().getName().getName())
											 .seq(entity.getId().getSeq())
											 .appointmentTypeCode(entity.getAppointmentTypeCode())
											 .appointmentDate(entity.getAppointmentDate())
											 .appointmentEndDate(entity.getAppointmentEndDate())
											 .recordName(entity.getRecordName())
											 .comment(entity.getComment())
											 .isCompleted(entity.getIsCompleted())
											 .blngDeptId(info.map(AppointmentInformation::getBlngDeptId).orElse(null))
											 .workDeptId(info.map(AppointmentInformation::getWorkDeptId).orElse(null))
											 .jobGroupCode(info.map(AppointmentInformation::getJobGroupCode).orElse(null))
											 .jobPositionCode(info.map(AppointmentInformation::getJobPositionCode).orElse(null))
											 .occupationCode(info.map(AppointmentInformation::getOccupationCode).orElse(null))
											 .jobGradeCode(info.map(AppointmentInformation::getJobGradeCode).orElse(null))
											 .payStepCode(info.map(AppointmentInformation::getPayStepCode).orElse(null))
											 .jobCode(info.map(AppointmentInformation::getJobCode).orElse(null))
											 .dutyResponsibilityCode(info.map(AppointmentInformation::getDutyResponsibilityCode).orElse(null))
											 .build();									
		}
		
		private AppointmentInformation newAppointmentInformation() {
			return new AppointmentInformation(blngDeptId
											 ,workDeptId
											 ,jobGroupCode
											 ,jobPositionCode
											 ,occupationCode
											 ,jobGradeCode
											 ,payStepCode
											 ,jobCode
											 ,dutyResponsibilityCode);
		}
	}
		
}
