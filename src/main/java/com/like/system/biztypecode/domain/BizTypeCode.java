package com.like.system.biztypecode.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "BIZTYPECODE")
public class BizTypeCode extends AbstractAuditEntity {

	@EmbeddedId
	BizTypeCodeId id;
	
	@Column(name="CODE_NM")
	String codeName;
		
	@Column(name="USE_YN")
	Boolean useYn = true;
	
	@Column(name="PRT_SEQ")
	Integer sequence;
	
	@Column(name="CMT")
	String comment;
	
	@ManyToOne
	@MapsId("typeId")	//기본키를 외래키로 쓰는경우 @MapsId 사용, 아니면 @JOinColumn 사용 
	BizType bizType;

	public BizTypeCode(BizType bizType
			          ,String code
			          ,String name            
					  ,String comment) {
		Objects.requireNonNull(bizType, "업무 구분은 필수 입력 값입니다.");
		if (!StringUtils.hasText(code)) throw new IllegalArgumentException("CODE는 필수 입력 값입니다.");
		
		this.bizType = bizType;
		this.id = new BizTypeCodeId(bizType.getId().getTypeId(), code);
		this.codeName = name;
		this.useYn = true;
		this.sequence = 0;
		this.comment = comment;
	}
	
	public BizTypeCode(BizType bizType
			          ,String code
			          ,String name
			          ,Boolean useYn
			          ,Integer sequence 
					  ,String comment) {
		Objects.requireNonNull(bizType, "업무 구분은 필수 입력 값입니다.");
		if (!StringUtils.hasText(code)) throw new IllegalArgumentException("CODE는 필수 입력 값입니다.");
		
		this.bizType = bizType;
		this.id = new BizTypeCodeId(bizType.getId().getTypeId(), code);
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
