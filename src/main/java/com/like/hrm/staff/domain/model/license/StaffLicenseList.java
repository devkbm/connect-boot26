package com.like.hrm.staff.domain.model.license;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.NoArgsConstructor;

/**
 * 자격면허
 * @author 김병민
 *
 */
@NoArgsConstructor
@Embeddable
public class StaffLicenseList {
	
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<StaffLicense> licenseList = new LinkedHashSet<>();
	
	public StaffLicense get(Long id) {
		
		return this.licenseList.stream()
							   .filter(e -> e.getLicenseId().equals(id))
							   .findFirst()
							   .orElse(null);
	}
	
	public void add(StaffLicense license) {
		if (this.licenseList == null) {
			this.licenseList = new LinkedHashSet<>();
		}
		
		this.licenseList.add(license);
	}	
	
	public void remove(Long id) {		
		this.licenseList.removeIf(e -> e.getLicenseId().equals(id));			
	}
		
	long getNextSequence() {
		long maxSeq = 0;
		
		if (this.licenseList == null || this.licenseList.isEmpty()) {
			maxSeq = 0;
		} else {
			/*
			maxSeq = this.licenseList.stream()
							  		 .mapToLong(e -> e.getId().getSeq())
							  		 .max()
							  		 .getAsLong();*/
							  
		}
					
		return maxSeq + 1;
	}
}
