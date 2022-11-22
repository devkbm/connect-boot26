package com.like.hrm.staff.domain.model.family;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.like.hrm.staff.domain.model.Staff;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class StaffFamilyList {

	/**
	 * 직원 가족이력
	 */
	@OrderBy("seq asc")
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<StaffFamily> familyList = new LinkedHashSet<>();
	
	public Stream<StaffFamily> getStream() {
		return this.familyList.stream();
	}
	
	public StaffFamily get(Staff staff, Long seq) {					
		return this.familyList.stream()
							  .filter(e -> e.getId().equals(new StaffFamilyId(staff, seq)))
							  .findFirst()
							  .orElse(null);
	}
	
	public void add(StaffFamily family) {		
		this.familyList.add(family);
	}	
	
	public void remove(Staff staff, Long seq) {		
		this.familyList.removeIf(e -> e.getId().equals(new StaffFamilyId(staff, seq)));			
	}
	
	long getNextSequence() {
		long maxSeq = 0;
		
		if (this.familyList == null || this.familyList.isEmpty()) {
			maxSeq = 0;
		} else {
			maxSeq = this.familyList.stream()
							  		.mapToLong(e -> e.getId().getSeq())
							  		.max()
							  		.getAsLong();							 
		}
					
		return maxSeq + 1;
	}
}
