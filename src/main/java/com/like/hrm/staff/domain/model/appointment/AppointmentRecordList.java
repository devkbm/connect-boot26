package com.like.hrm.staff.domain.model.appointment;

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
public class AppointmentRecordList {

	/**
	 * 직원 발령기록명단
	 */
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<AppointmentRecord> appointmentRecordList = new LinkedHashSet<>();
	
	public AppointmentRecord get(Long id) {
		
		return this.appointmentRecordList.stream()
							   .filter(e -> e.getId().equals(id))
							   .findFirst()
							   .orElse(null);
	}
	
	public void add(AppointmentRecord record) {
		
		if (this.appointmentRecordList == null) this.appointmentRecordList = new LinkedHashSet<>(); 		
		
		this.appointmentRecordList.add(record);
	}	
	
	public void remove(Long id) {		
		this.appointmentRecordList.removeIf(e -> e.getId().equals(id));			
	}
	
	public Stream<AppointmentRecord> stream() {
		return this.appointmentRecordList.stream();
	}
}
