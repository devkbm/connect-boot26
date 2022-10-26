package com.like.system.hierarchycode.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true, exclude = {"parentCode"})
@Getter
@Entity
@Table(name = "comcode")
@EntityListeners(AuditingEntityListener.class)
public class Code extends AbstractAuditEntity  {
					
	@EmbeddedId
	CodeId id;			
	
	@Column(name="CODE")
	String code;
	
	@Column(name="CODE_NAME")
	String codeName;
	
	@Column(name="CODE_NAME_ABBR")
	String codeNameAbbreviation;		
	
	@Column(name="FROM_DT")
	LocalDateTime fromDate;
	
	@Column(name="TO_DT")
	LocalDateTime toDate;
	
	@Column(name="HIERARCHY_LEVEL")
	int hierarchyLevel = 1;
	
	@Column(name="LOW_LEVEL_CODE_LENGTH")
	Integer lowLevelCodeLength;
	
	@Column(name="PRT_SEQ")
	int seq = 0;				
	
	@Column(name="cmt")
	String cmt;
			
	/**
	 * 상위 코드 참조용 컬럼
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value = {
        @JoinColumn(name="system_type_code", referencedColumnName="system_type_code", insertable = false, updatable = false),
        @JoinColumn(name="p_code_id", referencedColumnName="code_id", insertable = false, updatable = false)
    }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))          
	Code parentCode;
	
	@Column(name="P_CODE_ID")
	String _parentCode;

	@Builder
	public Code(String systemTypeCode
			   ,String code
			   ,String codeName
			   ,String codeNameAbbreviation
			   ,LocalDateTime fromDate
			   ,LocalDateTime toDate
			   ,int seq			   
			   ,Integer lowLevelCodeLength
			   ,String cmt
			   ,Code parentCode) {
		
		this.id = new CodeId(systemTypeCode, parentCode, code);
		this.code = code;
		this.codeName = codeName;
		this.codeNameAbbreviation = codeNameAbbreviation;		
		this.fromDate = fromDate;
		this.toDate = toDate;		
		this.seq = seq;		
		this.lowLevelCodeLength = lowLevelCodeLength;
		this.cmt = cmt;
		this.parentCode = parentCode;
		this._parentCode = parentCode == null ? null : parentCode.getId().getCodeId();
				
		this.hierarchyLevel();
	}
		
	public void modifyEntity(String codeName
							,String codeNameAbbreviation
							,LocalDateTime fromDate
							,LocalDateTime toDate
							,int seq							
							,Integer lowLevelCodeLength
							,String cmt) {
		this.codeName = codeName;
		this.codeNameAbbreviation = codeNameAbbreviation;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.seq = seq;		
		this.lowLevelCodeLength = lowLevelCodeLength;
		this.cmt = cmt;
	}

	public Code getParentCode() {
		return this.parentCode;
	}
		
	private void hierarchyLevel() {
		if ( this.parentCode == null ) {
			this.hierarchyLevel = 1; 
		} else {
			this.hierarchyLevel = this.parentCode.hierarchyLevel + 1;
		}
	}
					
}
