package com.like.hrm.staff.domain.model.appointment;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.like.hrm.staff.domain.model.Staff;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class AppointmentRecordList {

	/**
	 * 직원 발령기록명단
	 */
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<AppointmentRecord> appointmentRecordList = new LinkedHashSet<>();
	
	public Stream<AppointmentRecord> getStream() {
		return appointmentRecordList.stream();
	}
	
	public AppointmentRecord get(Staff staff, Long seq) {		
		return appointmentRecordList.stream()
							   		.filter(e -> e.getId().equals(new AppointmentRecordId(staff, seq)))
							   		.findFirst()
							   		.orElse(null);
	}
	
	public void add(AppointmentRecord record) {		
		if (this.appointmentRecordList == null) this.appointmentRecordList = new LinkedHashSet<>(); 		
		
		this.appointmentRecordList.add(record);
	}	
	
	public void remove(Staff staff, Long seq) {		
		this.appointmentRecordList.removeIf(e -> e.getId().equals(new AppointmentRecordId(staff, seq)));			
	}	
	
	long getNextSequence() {
		long maxSeq = 0;
		
		if (this.appointmentRecordList == null || this.appointmentRecordList.isEmpty()) {
			maxSeq = 0;
		} else {			
			maxSeq = this.appointmentRecordList.stream()
							  		.mapToLong(e -> e.getId().getSeq())
							  		.max()
							  		.getAsLong();										  
		}
					
		return maxSeq + 1;
	}
}
