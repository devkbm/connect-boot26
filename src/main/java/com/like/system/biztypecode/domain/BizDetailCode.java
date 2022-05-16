package com.like.system.biztypecode.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.like.system.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "BIZTYPEDETAILCODE")
public class BizDetailCode extends AuditEntity {

	@EmbeddedId
	BizDetailCodeId id;
	
	@Column(name="DETAIL_CODE_NAME")
	String codeName;
		
	@Column(name="USE_YN")
	Boolean useYn = true;
	
	@Column(name="PRT_SEQ")
	Integer sequence;
	
	@Column(name="CMT")
	String comment;
	
	@ManyToOne
	@MapsId("typeCode")	//기본키를 외래키로 쓰는경우 @MapsId 사용, 아니면 @JOinColumn 사용 
	BizTypeCode bizTypeCode;

	public BizDetailCode(BizTypeCode bizTypeCode
            ,String code
            ,String name            
		    ,String comment) {
		Objects.requireNonNull(bizTypeCode, "업무 구분은 필수 입력 값입니다.");
		
		this.bizTypeCode = bizTypeCode;
		this.id = new BizDetailCodeId(bizTypeCode.getId(), code);
		this.codeName = name;
		this.useYn = true;
		this.sequence = 0;
		this.comment = comment;
	}
	
	public BizDetailCode(BizTypeCode bizTypeCode
			            ,String code
			            ,String name
			            ,Boolean useYn
			            ,Integer sequence 
					    ,String comment) {
		Objects.requireNonNull(bizTypeCode, "업무 구분은 필수 입력 값입니다.");
		
		this.bizTypeCode = bizTypeCode;
		this.id = new BizDetailCodeId(bizTypeCode.getId(), code);
		this.codeName = name;
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;
	}
	
	public void modify(String codeName
					  ,Boolean useYn
					  ,Integer sequence
					  ,String comment) {
		this.codeName = codeName;
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;
	}
	
}
