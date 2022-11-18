package com.like.hrm.staff.boundary;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.schoolcareer.StaffSchoolCareer;

import lombok.AccessLevel;
import lombok.Builder;

public class SchoolCareerDTO {

	@Builder(access = AccessLevel.PRIVATE)
	public static record Form(
			String clientAppUrl,
			String organizationCode,			
			@NotEmpty String staffId,
			String staffNo,
			String staffName,
			Long seq,
			@NotEmpty String schoolCareerType,
			@NotEmpty String schoolCode,
			LocalDate fromDate,
			LocalDate toDate,
			String majorName,
			String pluralMajorName,
			String location,
			Integer lessonYear,
			String comment) {
		
		public StaffSchoolCareer newEntity(Staff staff) {
			StaffSchoolCareer entity = StaffSchoolCareer.builder()
														.staff(staff)
														.schoolCareerType(schoolCareerType)
														.schoolCode(schoolCode)
														.fromDate(fromDate)
														.toDate(toDate)
														.majorName(majorName)
														.pluralMajorName(pluralMajorName)
														.location(location)
														.lessonYear(lessonYear)
														.build(); 
			entity.setAppUrl(clientAppUrl);
			
			return entity; 					
		}
		
		public void modifyEnity(StaffSchoolCareer entity) {
			entity.modifyBuilder()
			      .schoolCareerType(schoolCareerType)
			      .schoolCode(schoolCode)
			      .fromDate(fromDate)
			      .toDate(toDate)
			      .majorName(majorName)
			      .pluralMajorName(pluralMajorName)
			      .location(location)
			      .lessonYear(lessonYear)
			      .comment(comment)
				  .modify();			
			
			entity.setAppUrl(clientAppUrl);
		}	
		
		public static Form convert(StaffSchoolCareer entity) {
			if (entity == null) return null;			
									
			return Form.builder()
					   .staffId(entity.getStaff().getId())
					   .staffNo(entity.getStaff().getStaffNo())
					   .staffName(entity.getStaff().getName().getName())
					   .seq(entity.getId().getSeq())
					   .schoolCareerType(entity.getSchoolCareerType())
					   .schoolCode(entity.getSchoolCode())
					   .fromDate(entity.getPeriod().getFrom())
					   .toDate(entity.getPeriod().getTo())
					   .majorName(entity.getMajorName())
					   .pluralMajorName(entity.getPluralMajorName())
					   .location(entity.getLocation())
					   .lessonYear(entity.getLessonYear())
					   .comment(entity.getComment())
					   .build();												 						
		}
	}
}
