package com.like.hrm.staff.domain.model.schoolcareer;

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
public class StaffSchoolCareerList {
	
	@OrderBy("seq asc")
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<StaffSchoolCareer> schoolCareerList = new LinkedHashSet<>();
	
	public Stream<StaffSchoolCareer> getStream() {
		return this.schoolCareerList.stream();
	}
	
	public StaffSchoolCareer get(Staff staff, Long seq) {
		return this.schoolCareerList.stream()
								 	.filter(e -> e.getId().equals(new StaffSchoolCareerId(staff, seq)))
								 	.findFirst()
								 	.orElse(null);
	}
	
	public void add(StaffSchoolCareer education) {				
		this.schoolCareerList.add(education);
	}
	
	public void remove(Staff staff, Long seq) {
		this.schoolCareerList.removeIf(e -> e.getId().equals(new StaffSchoolCareerId(staff, seq)));
	}
	
	long getNextSequence() {
		long maxSeq = 0;
		
		if (this.schoolCareerList == null || this.schoolCareerList.isEmpty()) {
			maxSeq = 0;
		} else {			
			maxSeq = this.schoolCareerList.stream()
							  		 .mapToLong(e -> e.getId().getSeq())
							  		 .max()
							  		 .getAsLong();							 
		}
					
		return maxSeq + 1;
	}
}
