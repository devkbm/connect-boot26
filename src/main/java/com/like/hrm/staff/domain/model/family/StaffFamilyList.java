package com.like.hrm.staff.domain.model.family;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class StaffFamilyList {

	/**
	 * 직원 가족이력
	 */
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<StaffFamily> familyList = new LinkedHashSet<>();
	
	public Stream<StaffFamily> getStream() {
		return this.familyList.stream();
	}
	
	public StaffFamily get(StaffFamilyId id) {
					
		return this.familyList.stream()
							  .filter(e -> e.getId().equals(id))
							  .findFirst()
							  .orElse(null);
	}
	
	public void add(StaffFamily family) {		
		this.familyList.add(family);
	}	
	
	public void remove(StaffFamilyId id) {		
		this.familyList.removeIf(e -> e.getId().equals(id));			
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
