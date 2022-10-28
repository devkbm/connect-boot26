package com.like.hrm.staff.domain.model.dutyresponsibility;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.like.hrm.staff.domain.model.Staff;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class StaffDutyList {

	/**
	 * 직책이력
	 */
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<StaffDuty> staffDutyList = new LinkedHashSet<>();
		
	public StaffDuty get(Staff staff, Integer seq) {
		
		return this.staffDutyList.stream()
								 .filter(e -> e.getId().equals(new StaffDutyId(staff, seq)))
								 .findFirst()
								 .orElse(null);
				
	}
		
	public void remove(StaffDutyId id) {
		this.staffDutyList.removeIf(e -> e.getId().equals(id));
	}
	
	void add(StaffDuty entity) {
		if (this.staffDutyList == null) {
			this.staffDutyList = new LinkedHashSet<>();
		}
		
		if (!this.staffDutyList.contains(entity)) {
			this.staffDutyList.add(entity);
		}			
	}
	
	int getNextSeq() {
		int maxSeq = 0;
		
		if (this.staffDutyList == null || this.staffDutyList.isEmpty()) {
			maxSeq = 0;
		} else {
			maxSeq = this.staffDutyList.stream()
							  		   .mapToInt(e -> e.getId().getSeq())
							  		   .max()
							  		   .getAsInt();
							  
		}
					
		return maxSeq + 1;
	}
}
