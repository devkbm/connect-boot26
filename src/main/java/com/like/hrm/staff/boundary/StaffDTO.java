package com.like.hrm.staff.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.like.hrm.staff.domain.model.QStaff;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffContact;
import com.like.hrm.staff.domain.model.StaffName;
import com.like.system.core.jpa.vo.Address;
import com.like.system.core.jpa.vo.PhoneNumber;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.Builder;

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
			entity.modifyEntity(StaffName.of(name, nameEng, nameChi)
					 		   ,this.birthday);
		}
	}
	
	@Builder(access = AccessLevel.PRIVATE)
	public static record FormContact(
			String clientAppUrl,
			String organizationCode,			
			@NotEmpty String staffId,
			String staffNo,
			String staffName,
			String homeAddressType,
			String homePostNumber,
			String homeMainAddress,
			String homeSubAddress,
			String extensionNumber,
			String mobileNumber
			) {
		
		public StaffContact newEntity() {		
			return new StaffContact(new Address(homeAddressType, homePostNumber, homeMainAddress, extensionNumber), new PhoneNumber(extensionNumber), new PhoneNumber(mobileNumber));
		}
					
		public static FormContact convert(Staff entity) {			
			
			Optional<StaffContact> contact = Optional.ofNullable(entity.getContact());
								
			return FormContact.builder()
					 		  .staffId(entity.getId())
					 		  .homeAddressType(contact.map(StaffContact::getHome).map(Address::getAddress_type).orElse(null))
					 		  .homePostNumber(contact.map(StaffContact::getHome).map(Address::getPost_number).orElse(null))
					 		  .homeMainAddress(contact.map(StaffContact::getHome).map(Address::getMain_address).orElse(null))
					 		  .homeSubAddress(contact.map(StaffContact::getHome).map(Address::getSub_address).orElse(null))					 		  
					 		  .extensionNumber(contact.map(StaffContact::getExtensionNumber).map(PhoneNumber::getNumber).orElse(null))
					 		  .mobileNumber(contact.map(StaffContact::getMobileNumber).map(PhoneNumber::getNumber).orElse(null))					 		  					 		  					 		  					 		
							  .build();
							  
		}
	}		

}
