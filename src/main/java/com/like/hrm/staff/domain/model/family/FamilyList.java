package com.like.hrm.staff.domain.model.family;

import java.util.LinkedHashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class FamilyList {

	/**
	 * 직원 가족이력
	 */
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<Family> familyList = new LinkedHashSet<>();
	
	public Family get(Long id) {
		
		return this.familyList.stream()
							   .filter(e -> e.getId().equals(id))
							   .findFirst()
							   .orElse(null);
	}
	
	public void add(Family family) {
		if (this.familyList == null) {
			this.familyList = new LinkedHashSet<>();
		}
		
		this.familyList.add(family);
	}	
	
	public void remove(Long id) {		
		this.familyList.removeIf(e -> e.getId().equals(id));			
	}
}
