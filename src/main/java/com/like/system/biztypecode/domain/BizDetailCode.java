package com.like.system.biztypecode.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.like.system.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "BIZTYPEDETAILCODE")
public class BizDetailCode extends AuditEntity {

	@EmbeddedId
	private BizDetailCodeId id;
	
	@Column(name="DETAIL_CODE_NAME")
	private String codeName;
		
	@Column(name="USE_YN")
	private Boolean useYn = true;
	
	@Column(name="PRT_SEQ")
	private Integer sequence;
	
	@Column(name="CMT")
	private String comment;
	
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
