package com.like.hrm.hrmtypecode.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>부서 유형 코드 기준 정보</p> 
 * [상세] <br/>
 * 1. 
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMCODE")
public class HrmTypeDetailCode implements Serializable {		
	
	private static final long serialVersionUID = 5468996305272335478L;

	@EmbeddedId		
	private HrmTypeDetailCodeId id;		
	
	@Column(name="CODE_NM")
	private String codeName;
		
	@Column(name="USE_YN")
	private boolean useYn = true;

	@Column(name="PRT_SEQ")
	private Integer sequence;
	
	@Column(name="CMT")
	private String comment;			
	
	@Transient
	private String relCode;
		
	public HrmTypeDetailCode(HrmTypeDetailCodeId id						 
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
