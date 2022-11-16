package com.like.hrm.staff.domain.model.family;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.like.hrm.staff.domain.model.Staff;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StaffFamilyId implements Serializable {
	
	private static final long serialVersionUID = -8748836639137047169L;
	
	@Column(name="STAFF_ID")
	String staffId;
		
	@Column(name="SEQ")
	Long seq;		
	
	public StaffFamilyId(Staff staff) {
		this.staffId = staff.getId();
		this.seq = staff.getFamilyList().getNextSequence();
	}
	
	public StaffFamilyId(Staff staff, Long seq) {
		this.staffId = staff.getId();
		this.seq = seq;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaffFamilyId other = (StaffFamilyId) obj;
		return Objects.equals(seq, other.seq) && Objects.equals(staffId, other.staffId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(seq, staffId);
	}	
	
	
}
