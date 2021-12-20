package com.like.hrm.hrmtypecode.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.system.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMTYPECODE")
public class HrmType extends AuditEntity {
		
	@Id
	@Column(name="TYPE_CODE")
	private String id;
	
	@Column(name="TYPE_CODE_NAME")
	private String name;
	
	@Column(name="USE_YN")
	private boolean useYn = true;
	
	@Column(name="PRT_SEQ")
	private Integer sequence;
	
	@Enumerated(EnumType.STRING)
	@Column(name="APPOINT_TYPE_CODE")
	private AppointmentTypeEnum appointmentType;
				
	@Column(name="CMT")
	private String comment;		
	
	public HrmType(String id
				  ,String name
				  ,boolean useYn
				  ,Integer sequence
				  ,AppointmentTypeEnum hrmType				  
				  ,String comment) {		
		this.appointmentType = hrmType;
		this.id = id;
		this.name = name;
		this.useYn = useYn;
		this.sequence = sequence;
		this.appointmentType = hrmType;
		this.comment = comment;
	}
	
	public void modify(String name
					  ,boolean useYn
					  ,Integer sequence
					  ,AppointmentTypeEnum hrmType
					  ,String comment ) {		
		this.name = name;
		this.useYn = useYn;
		this.sequence = sequence;
		this.appointmentType = hrmType;
		this.comment = comment;
	}	
	
}
