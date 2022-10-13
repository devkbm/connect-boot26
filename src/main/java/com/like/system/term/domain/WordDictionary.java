package com.like.system.term.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 단어 <br> 
 * 용어사전 등록 후보 
 */
@Getter
@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "COMTERMWORD")
@EntityListeners(AuditingEntityListener.class)
public class WordDictionary extends AbstractAuditEntity {

	@Id
	@Column(name="LOGICAL_NAME", unique = true)
	String logicalName;
		
	@Column(name="LOGICAL_NAME_ENG")
	String logicalNameEng;
	
	@Column(name="PHYSICAL_NAME")
	String physicalName;
	
	@Column(name="CMT")
	String comment;
	
	public WordDictionary(String logicalName, String logicalNameEng, String physicalName, String comment) {
		Objects.requireNonNull(logicalName, "필수 입력 값입니다.");
		
		this.logicalName = logicalName;
		this.logicalNameEng = logicalNameEng;
		this.physicalName= physicalName;
		this.comment = comment;
	}
}
