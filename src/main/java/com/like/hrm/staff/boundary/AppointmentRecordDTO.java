package com.like.hrm.staff.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.appointment.AppointmentInformation;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AppointmentRecordDTO {
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class FormStaffAppointmentRecord implements Serializable {
				
		private static final long serialVersionUID = -907086321137174863L;

		@NotEmpty
		private String staffId;
		
		private Long id;			
				
		private LocalDate appointmentDate;
				
		private LocalDate appointmentEndDate;
				
		private String recordName;
				
		private String comment;
				
		private String processWatingYn;
		
		private String blngDeptCode;
		
		private String workDeptCode;
		
		private String jobGroupCode;
		
		private String jobPositionCode;
		
		private String occupationCode;
		
		private String jobGradeCode;
		
		private String payStepCode;
		
		private String jobCode;
		
		private String dutyResponsibilityCode;
		
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
			
			return FormStaffAppointmentRecord.builder()
							 			.staffId(entity.getStaff().getId())
							 			.id(entity.getId())
							 			.appointmentDate(entity.getAppointmentDate())
							 			.appointmentEndDate(entity.getAppointmentEndDate())
							 			.recordName(entity.getRecordName())
							 			.comment(entity.getComment())
							 			.processWatingYn(entity.getProcessWatingYn())
							 			.blngDeptCode(info.map(AppointmentInformation::getBlngDeptCode).orElse(null))
							 			.workDeptCode(info.map(AppointmentInformation::getWorkDeptCode).orElse(null))
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
