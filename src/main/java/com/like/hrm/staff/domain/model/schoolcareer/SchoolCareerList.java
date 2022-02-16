package com.like.hrm.staff.domain.model.schoolcareer;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class SchoolCareerList {

	/**
	 * 학력이력
	 */
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<SchoolCareer> schoolCareerList = new LinkedHashSet<>();
	
	public SchoolCareer get(Long id) {
		return this.schoolCareerList.stream()
								 	.filter(e -> e.getId().equals(id))
								 	.findFirst()
								 	.orElse(null);
	}
	
	public void add(SchoolCareer education) {
		if (this.schoolCareerList == null) {
			this.schoolCareerList = new LinkedHashSet<>();
		}
		
		this.schoolCareerList.add(education);
	}
	
	public void remove(Long id) {
		this.schoolCareerList.removeIf(e -> e.getId().equals(id));
	}
}
