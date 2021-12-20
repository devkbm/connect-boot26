package com.like.hrm.staff.domain.model.dutyresponsibility;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class StaffDutyList {

	/**
	 * 직책이력
	 */
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<StaffDuty> staffDutyList = new LinkedHashSet<>();
	
	public StaffDuty get(StaffDutyId id) {
		return this.staffDutyList.stream()
								 .filter(e -> e.getId().equals(id))
								 .findFirst()
								 .orElse(null);
	}
	
	public void add(StaffDuty education) {
		if (this.staffDutyList == null) {
			this.staffDutyList = new LinkedHashSet<>();
		}
		
		this.staffDutyList.add(education);
	}
	
	public void remove(StaffDutyId id) {
		this.staffDutyList.removeIf(e -> e.getId().equals(id));
	}
}
