package com.like.hrm.staff.domain.model.appointment;

import java.io.Serializable;

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
public class AppointmentRecordId implements Serializable {
	
	private static final long serialVersionUID = -9052607243196727987L;

	@Comment("직원ID")
	@Column(name="STAFF_ID")
	String staffId;
	
	@Comment("등록순번")
	@Column(name="SEQ")
	Long seq;
	
	public AppointmentRecordId(Staff staff, Long seq) {
		this.staffId = staff.getId();
		this.seq = seq;
	}
}
