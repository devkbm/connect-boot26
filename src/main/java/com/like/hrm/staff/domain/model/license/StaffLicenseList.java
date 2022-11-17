package com.like.hrm.staff.domain.model.license;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

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
	
	public Stream<StaffLicense> getStream() {
		return this.licenseList.stream();
	}
	
	public StaffLicense get(StaffLicenseId id) {
		
		return this.licenseList.stream()
							   .filter(e -> e.getId().equals(id))
							   .findFirst()
							   .orElse(null);
	}
	
	public void add(StaffLicense license) {		
		this.licenseList.add(license);
	}	
	
	public void remove(StaffLicenseId id) {		
		this.licenseList.removeIf(e -> e.getId().equals(id));			
	}
		
	long getNextSequence() {
		long maxSeq = 0;
		
		if (this.licenseList == null || this.licenseList.isEmpty()) {
			maxSeq = 0;
		} else {
			
			maxSeq = this.licenseList.stream()
							  		 .mapToLong(e -> e.getId().getSeq())
							  		 .max()
							  		 .getAsLong();
							  
		}
					
		return maxSeq + 1;
	}
}
