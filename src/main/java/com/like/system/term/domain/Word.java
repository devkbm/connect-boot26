package com.like.system.term.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity
@Table(name = "COMTERMWORD")
@EntityListeners(AuditingEntityListener.class)
public class Word extends AbstractAuditEntity {

	@Id
	@Column(name="LOGICAL_NAME")
	String logicalName;
	
	@Column(name="PHYSICAL_NAME")
	String physicalName;
}
