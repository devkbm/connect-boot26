package com.like.hrm.staff.boundary;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.license.StaffLicense;

import lombok.AccessLevel;
import lombok.Builder;

public class LicenseDTO {
	@Builder(access = AccessLevel.PRIVATE)
	public static record Form(
			String clientAppUrl,
			String organizationCode,			
			@NotEmpty String staffId,
			String staffNo,
			String staffName,
			Long seq,
			@NotEmpty String licenseType,
			@NotEmpty String licenseNumber,
			LocalDate dateOfAcquisition,
			String certificationAuthority,
			String comment
			) {
		
		public StaffLicense newEntity(Staff staff) {
			StaffLicense entity = StaffLicense.builder()
											  .staff(staff)
											  .licenseType(licenseType)
											  .licenseNumber(licenseNumber)
											  .dateOfAcquisition(dateOfAcquisition)
											  .certificationAuthority(certificationAuthority)
											  .comment(comment)
											  .build();
			entity.setAppUrl(clientAppUrl);
			
			return entity; 			
		}
		
		public void modifyEntity(StaffLicense entity) {
			entity.modifyBuilder()			
			      .licenseType(licenseType)
			      .licenseNumber(licenseNumber)
			      .dateOfAcquisition(dateOfAcquisition)
			      .certificationAuthority(certificationAuthority)
			      .comment(comment)
				  .modify();
			
			entity.setAppUrl(clientAppUrl);
		}	
		
		public static Form convert(StaffLicense entity)  {
			if (entity == null) return null; 
			
			return Form.builder()
					   .staffId(entity.getStaff().getId())
					   .staffNo(entity.getStaff().getStaffNo())
					   .staffName(entity.getStaff().getName().getName())
					   .seq(entity.getId().getSeq())
					   .licenseType(entity.getLicenseType())
					   .licenseNumber(entity.getLicenseNumber())
					   .dateOfAcquisition(entity.getDateOfAcquisition())
					   .certificationAuthority(entity.getCertificationAuthority())
					   .comment(entity.getComment())
					   .build();			
		}
	}
}
