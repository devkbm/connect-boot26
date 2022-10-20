package com.like.hrm.hrmcode.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMCODE")
public class HrmCode extends AbstractAuditEntity {			

	@EmbeddedId		
	HrmCodeId id;		
	
	@Column(name="CODE_NM")
	String codeName;
		
	@Column(name="USE_YN")
	boolean useYn = true;

	@Column(name="PRT_SEQ")
	Integer sequence;
	
	@Column(name="CMT")
	String comment;			
	
	@Transient
	String relCode;
		
	public HrmCode(HrmCodeId id						 
			 	  ,String codeName
			 	  ,boolean useYn
			 	  ,Integer sequence
			 	  ,String comment) {		
		this.id = id;				
		this.codeName = codeName;		
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;
	}
		
	public void modify(String codeName
					  ,boolean useYn
					  ,Integer sequence
					  ,String comment ) {	
		this.codeName = codeName;
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;
	}	
	
}
