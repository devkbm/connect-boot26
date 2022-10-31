package com.like.hrm.staff.boundary;

import java.time.LocalDate;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.dutyresponsibility.StaffDuty;

import lombok.AccessLevel;
import lombok.Builder;

public class StaffDutyResponsibilityDTO {

	@Builder(access = AccessLevel.PRIVATE)
	public static record Form(
			String clientAppUrl,
			String organizationCode,
			String staffId,
			String staffNo,
			String staffName,
			Integer seq,
			String dutyResponsibilityCode,
			String dutyResponsibilityName,
			LocalDate fromDate,
			LocalDate toDate,
			Boolean isPayApply			
			) {
		
		public StaffDuty newEntity(Staff staff) {		
			StaffDuty entity = new StaffDuty(staff, dutyResponsibilityCode, fromDate, toDate, isPayApply);
								
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
		
		public void modifyEntity(StaffDuty entity) {
			entity.modifyEntity(dutyResponsibilityCode, fromDate, toDate, isPayApply);
		}
		
		
		public static Form convert(StaffDuty entity) {			
			if (entity == null) return null;
			
			return Form.builder()
					   .staffId(entity.getStaff().getId())
					   .staffNo(entity.getStaff().getStaffNo())
					   .staffName(entity.getStaff().getName().getName())
					   .seq(entity.getId().getSeq())
					   .dutyResponsibilityCode(entity.getDutyResponsibilityCode())
					   .dutyResponsibilityName(entity.getDutyResponsibilityCode())
					   .fromDate(entity.getFromDate())
					   .toDate(entity.getToDate())
					   .isPayApply(entity.getIsPayApply())
					   .build();
		}
		
	}
}
