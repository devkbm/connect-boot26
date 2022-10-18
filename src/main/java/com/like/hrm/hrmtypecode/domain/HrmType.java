package com.like.hrm.hrmtypecode.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMCODETYPE")
public class HrmType extends AbstractAuditEntity {
		
	@Id
	@Column(name="TYPE_ID")
	private String id;
	
	@Column(name="TYPE_NAME")
	private String name;
	
	@Column(name="USE_YN")
	private boolean useYn = true;
	
	@Column(name="PRT_SEQ")
	private Integer sequence;
					
	@Column(name="CMT")
	private String comment;		
	
	public HrmType(String id
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
