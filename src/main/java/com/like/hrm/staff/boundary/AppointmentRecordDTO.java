package com.like.hrm.staff.boundary;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.appointment.AppointmentInformation;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;

public class AppointmentRecordDTO {
	
	public record FormStaffAppointmentRecord(
			@NotEmpty
			String staffId,
			Long id,
			LocalDate appointmentDate,
			LocalDate appointmentEndDate,			
			String recordName,
			String comment,				
			String processWatingYn,
			String blngDeptCode,			
			String workDeptCode,			
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
			
			return new AppointmentRecord(staff,appointmentDate, appointmentEndDate, recordName, comment, info);				
		}
		
		public void modifyEntity(AppointmentRecord entity) {
			
			AppointmentInformation info = newAppointmentInformation();
			
			entity.modify(appointmentDate
						 ,appointmentEndDate
						 ,recordName
						 ,comment
						 ,info);									
		}
				
		public static FormStaffAppointmentRecord convert(AppointmentRecord entity) {
			if (entity == null) return null;																													

			Optional<AppointmentInformation> info = Optional.ofNullable(entity.getInfo());
			
			return new FormStaffAppointmentRecord(entity.getStaff().getId()
												 ,entity.getId()
												 ,entity.getAppointmentDate()
												 ,entity.getAppointmentEndDate()
												 ,entity.getRecordName()
												 ,entity.getComment()
												 ,entity.getProcessWatingYn()
												 ,info.map(AppointmentInformation::getBlngDeptCode).orElse(null)
												 ,info.map(AppointmentInformation::getWorkDeptCode).orElse(null)
												 ,info.map(AppointmentInformation::getJobGroupCode).orElse(null)
												 ,info.map(AppointmentInformation::getJobPositionCode).orElse(null)
												 ,info.map(AppointmentInformation::getOccupationCode).orElse(null)
												 ,info.map(AppointmentInformation::getJobGradeCode).orElse(null)
												 ,info.map(AppointmentInformation::getPayStepCode).orElse(null)
												 ,info.map(AppointmentInformation::getJobCode).orElse(null)
												 ,info.map(AppointmentInformation::getDutyResponsibilityCode).orElse(null));					
		}
		
		private AppointmentInformation newAppointmentInformation() {
			return new AppointmentInformation(blngDeptCode
											 ,workDeptCode
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
