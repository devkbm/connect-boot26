package com.like.hrm.staff.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.like.hrm.staff.domain.model.QStaff;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffName;
import com.like.hrm.staff.domain.model.family.Family;
import com.like.hrm.staff.domain.model.license.License;
import com.like.hrm.staff.domain.model.schoolcareer.SchoolCareer;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public class StaffDTO {
		
	@JsonInclude(Include.NON_EMPTY)
	public record SearchStaff(
			LocalDate referenceDate,
			String staffId,
			String name,
			String deptType,
			String deptCode,
			List<String> deptCodeList,
			String deptName,
			String jobType,
			String jobCode
			) {
		
		private static final QStaff qStaff = QStaff.staff;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();					
			
			builder				
				.and(likeId(this.staffId))
				.and(likeName(this.name));				
			
			return builder;
		}
		
		private BooleanExpression likeId(String id) {
			return hasText(id) ? qStaff.id.like("%"+id+"%") : null;					
		}
		
		private BooleanExpression likeName(String name) {
			return hasText(name) ? qStaff.name.name.like("%"+name+"%") : null;			
		}	
	}	
			
	public record NewStaff(
			String clientAppUrl,
			String organizationCode,			
			@NotEmpty(message = "직원번호는 필수 입력 값입니다.")
			String staffNo,
			@NotEmpty(message = "이름은 필수 입력 값입니다.")
			String name,
			@NotEmpty(message = "주민등록번호는 필수 입력 값입니다.")
			String residentRegistrationNumber,
			String nameEng,
			String nameChi			
			) {	
		
		public String getStaffId() {
			return this.organizationCode + "_" + this.staffNo;
		}
		
	}
		
	public record ResponseStaff(
			String staffId,
			String organizationCode,
			String staffNo,
			String name,
			String nameEng,
			String nameChi,
			String residentRegistrationNumber,
			String gender,
			LocalDate birthday,
			String imagePath
			) {
				
		public static ResponseStaff convert(Staff entity) {
			
			if (entity == null) return null;			
			
			var name = entity.getName();
			
			return new ResponseStaff(entity.getId()
									,entity.getOrganizationCode()
									,entity.getStaffNo()
								   	,name.getName()
								   	,name.getNameEng()
								   	,name.getNameChi()
								   	,entity.getResidentRegistrationNumber().getNumber()
								   	,entity.getGender()
								   	,entity.getBirthday()
								   	,entity.getImagePath());								   
								   
		}
	}
			
	@JsonIgnoreProperties(ignoreUnknown = true)
	public record FormStaff(
			@NotEmpty(message = "직원번호는 필수 입력 값입니다.")
			String staffId,
			String name,
			String nameEng,
			String nameChi,
			String gender,
			LocalDate birthday
			) {
		
		public void modifyEntity(Staff entity) {
			entity.modifyEntity(new StaffName(name, nameEng, nameChi)
					 		   ,this.birthday);
		}
	}
		
	public record FormEducation(
			@NotEmpty
			String staffId,
			Long educationId,
			@NotEmpty
			String schoolCareerType,
			@NotEmpty
			String schoolCode,
			String comment) {
		
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
			if (entity == null) return null;			
			
			return new FormEducation(entity.getStaff().getId()
									,entity.getId()
									,entity.getSchoolCareerType()
									,entity.getSchoolCode()
									,entity.getComment()); 								
		}
	}
	
	public record FormLicense(
			@NotEmpty
			String staffId,
			Long licenseId,
			@NotEmpty
			String licenseType,
			@NotEmpty
			String licenseCode,
			String comment
			) {
		
		public License newEntity(Staff staff) {
			return new License(staff
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
			if (entity == null) return null; 
			
			return new FormLicense(entity.getStaff().getId()
								  ,entity.getLicenseId()
								  ,entity.getLicenseType()
								  ,entity.getLicenseCode()
								  ,entity.getComment());
							  
		}
	}
		
	
	public record FormFamily(
			@NotEmpty
			String staffId,
			Long id,
			String name,
			String residentRegistrationNumber,
			String relation,
			String occupation,
			String schoolCareerType,
			String comment
			) {
		
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
			if (entity == null) return null;
			
			return new FormFamily(entity.getStaff().getId()
								 ,entity.getId()
								 ,entity.getName()
								 ,entity.getResidentRegistrationNumber()
								 ,entity.getRelation()
								 ,entity.getOccupation()
								 ,entity.getSchoolCareerType()
								 ,entity.getComment());							 							 							 							 						
		}
	}	
	

}
