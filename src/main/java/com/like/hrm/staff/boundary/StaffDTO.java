package com.like.hrm.staff.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.like.cooperation.team.boundary.TeamMemberDTO;
import com.like.hrm.staff.domain.model.QStaff;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffName;
import com.like.hrm.staff.domain.model.family.Family;
import com.like.hrm.staff.domain.model.license.License;
import com.like.hrm.staff.domain.model.schoolcareer.SchoolCareer;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

public class StaffDTO {
		
	@Slf4j
	@Data
	@JsonInclude(Include.NON_EMPTY)	
	public static class SearchStaff implements Serializable {			
		
		private static final long serialVersionUID = -3725100691674283297L;

		private final QStaff qStaff = QStaff.staff;		

		LocalDate referenceDate;
		
		String staffId;
		
		String name;
		
		String deptType;
		
		String deptCode;
		
		List<String> deptCodeList;
					
		String deptName;
		
		String jobType;
		
		String jobCode;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			log.info(this.deptType + " : "+ this.deptCodeList);
			
			builder				
				.and(likeId(this.staffId))
				.and(likeName(this.name));				
			
			return builder;
		}
		
		private BooleanExpression likeId(String id) {
			if (!StringUtils.hasText(id)) return null;
			
			return qStaff.id.like("%"+id+"%");
		}
		
		private BooleanExpression likeName(String name) {
			if (!StringUtils.hasText(name)) return null;
			
			return qStaff.name.name.like("%"+name+"%");
		}			
					
		
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class NewStaff implements Serializable {
		
		private static final long serialVersionUID = 5189496256963058913L;	
				
		@NotEmpty(message = "직원번호는 필수 입력 값입니다.")
		private String staffId;
		
		@NotEmpty(message = "이름은 필수 입력 값입니다.")
		private String name;

		private String nameEng;
		
		private String nameChi;
				
		@NotEmpty(message = "주민등록번호는 필수 입력 값입니다.")
		private String residentRegistrationNumber;	
	}
	
	public record NewStaffRec(
			@NotEmpty(message = "직원번호는 필수 입력 값입니다.")
			String staffId,
			@NotEmpty(message = "이름은 필수 입력 값입니다.")
			String name,
			String nameEng,
			String nameChi,
			@NotEmpty(message = "주민등록번호는 필수 입력 값입니다.")
			String residentRegistrationNumber
			) {	
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ResponseStaff implements Serializable {
				
		private static final long serialVersionUID = 3650310845683492073L;

		String staffId;	
		
		String name;
		
		String nameEng;
		
		String nameChi;			
					
		String residentRegistrationNumber;
		
		String gender;
		
		LocalDate birthday;				
		
		String imagePath;			
		
		public static ResponseStaff convert(Staff entity) {
									
			if (entity == null) return null;
			
			return ResponseStaff.builder()
								   .staffId(entity.getId())
								   .name(entity.getName().getName())
								   .nameEng(entity.getName().getNameEng())
								   .nameChi(entity.getName().getNameChi())
								   .residentRegistrationNumber(entity.getResidentRegistrationNumber().getNumber())
								   .gender(entity.getGender())
								   .birthday(entity.getBirthday())
								   .imagePath(entity.getImagePath())								   
								   .build();
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class FormStaff implements Serializable {
								
		private static final long serialVersionUID = -3475382902805357777L;

		@NotEmpty(message = "직원번호는 필수 입력 값입니다.")
		private String staffId;
				
		private String name;

		private String nameEng;
		
		private String nameChi;			
						
		private String gender;	
		
		private LocalDate birthday;
		
		public void modifyEntity(Staff entity) {
			entity.modifyEntity(new StaffName(name, nameEng, nameChi)
					 		   ,this.birthday);
		}
	}
			
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class FormEducation implements Serializable {					

		private static final long serialVersionUID = -8768170007000992707L;

		@NotEmpty
		private String staffId;
			
		private Long educationId;
		
		@NotEmpty
		private String schoolCareerType;
				
		@NotEmpty
		private String schoolCode;
				
		private String comment;
		
		public SchoolCareer newEntity(Staff employee) {
			return new SchoolCareer(employee
								,this.schoolCareerType
								,this.schoolCode
								,this.comment);
		}
		
		public void modifyEnity(SchoolCareer entity) {
			entity.modifyEntity(schoolCareerType
							   ,schoolCode
							   ,comment);	
		}	
		
		public static FormEducation convert(SchoolCareer entity) {
			return FormEducation.builder()
								.staffId(entity.getStaff().getId())
								.educationId(entity.getId())
								.schoolCareerType(entity.getSchoolCareerType())
								.schoolCode(entity.getSchoolCode())
								.comment(entity.getComment())
								.build();
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class FormLicense implements Serializable {						

		private static final long serialVersionUID = -4765555653271244793L;

		@NotEmpty
		private String staffId;
		
		private Long licenseId;
		
		@NotEmpty
		private String licenseType;
				
		@NotEmpty
		private String licenseCode;
				
		private String comment;
		
		public License newEntity(Staff employee) {
			return new License(employee
							  ,this.licenseType
							  ,this.licenseCode
							  ,this.comment);
		}
		
		public void modifyEntity(License entity) {
			entity.modifyEntity(licenseType
							   ,licenseCode
							   ,comment);	
		}	
		
		public static FormLicense convert(License entity)  {
			return FormLicense.builder()
							  .staffId(entity.getStaff().getId())
							  .licenseId(entity.getLicenseId())
							  .licenseType(entity.getLicenseType())
							  .licenseCode(entity.getLicenseCode())
							  .comment(entity.getComment())
							  .build();
		}
	}	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class FormFamily implements Serializable {
				
		private static final long serialVersionUID = -8291690526903657079L;

		@NotEmpty
		private String staffId;
		
		private Long id;
		
		private String name;
				
		private String residentRegistrationNumber;
				
		private String relation;
				
		private String occupation;
				
		private String schoolCareerType;
				
		private String comment;
		
		public Family newEntity(Staff employee) {
			return new Family(employee
							 ,name
							 ,residentRegistrationNumber
							 ,relation
							 ,occupation
							 ,schoolCareerType
							 ,comment);					
		}
		
		public void modifyEntity(Family entity) {
			entity.modifyEntity(name
							   ,residentRegistrationNumber
							   ,relation
							   ,occupation
							   ,schoolCareerType
							   ,comment);
		}
		
		public static FormFamily convert(Family entity) {
			return FormFamily.builder()
							 .staffId(entity.getStaff().getId())
							 .id(entity.getId())
							 .name(entity.getResidentRegistrationNumber())
							 .relation(entity.getRelation())
							 .occupation(entity.getOccupation())
							 .schoolCareerType(entity.getSchoolCareerType())
							 .comment(entity.getComment())
							 .build();
		}
	}
	
	

}
