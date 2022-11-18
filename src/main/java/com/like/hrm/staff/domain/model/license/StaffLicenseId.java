package com.like.hrm.staff.domain.model.license;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Comment;

import com.like.hrm.staff.domain.model.Staff;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StaffLicenseId implements Serializable {
	
	private static final long serialVersionUID = -2126754308967909563L;

	@Comment("직원ID")
	@Column(name="STAFF_ID")
	String staffId;
		
	@Comment("등록순번")
	@Column(name="SEQ")
	Long seq;	
	
	public StaffLicenseId(Staff staff, Long seq) {
		this.staffId = staff.getId();
		this.seq = seq;
	}

	@Override
	public int hashCode() {
		return Objects.hash(seq, staffId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaffLicenseId other = (StaffLicenseId) obj;
		return Objects.equals(seq, other.seq) && Objects.equals(staffId, other.staffId);
	}
	
	
}
