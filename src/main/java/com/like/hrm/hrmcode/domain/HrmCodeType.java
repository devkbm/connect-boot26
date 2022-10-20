package com.like.hrm.hrmcode.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

//import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMCODETYPE")
public class HrmCodeType extends AbstractAuditEntity {
		
	@Id
	@Column(name="TYPE_ID")
	String id;
	
	@Column(name="TYPE_NM")
	String name;
	
	@Column(name="USE_YN")
	boolean useYn = true;
	
	@Column(name="PRT_SEQ")
	Integer sequence;
					
	@Column(name="CMT")
	String comment;		
	
	public HrmCodeType(String id
					  ,String name
					  ,boolean useYn
					  ,Integer sequence				  				 
					  ,String comment) {				
		this.id = id;
		this.name = name;
		this.useYn = useYn;
		this.sequence = sequence;		
		this.comment = comment;
	}
	
	public void modify(String name
					  ,boolean useYn
					  ,Integer sequence					  
					  ,String comment ) {		
		this.name = name;
		this.useYn = useYn;
		this.sequence = sequence;		
		this.comment = comment;
	}	
	
}
