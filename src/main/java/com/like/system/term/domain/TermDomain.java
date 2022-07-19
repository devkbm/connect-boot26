package com.like.system.term.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "COMTERMDOMAIN")
@EntityListeners(AuditingEntityListener.class)
public class TermDomain extends AbstractAuditEntity {
	
	public enum Database { ORACLE, MYSQL };
	
	@Id	
	@Column(name="DOMAIN_ID")
	String id;
	
	@Column(name="DB")
	@Enumerated(EnumType.STRING)
	Database database; 
	
	@Column(name="DOMAIN_NAME")
	String domainName;
		
	@Column(name="DATA_TYPE")
	String dataType;
	
	@Column(name="COLUMN_SIZE")
	String columnSize;
	
	@Builder
	public TermDomain(Database database, String domainName, String dataType, String columnSize) {
		this.id = database + "_" + domainName;
		this.database = database;
		this.domainName = domainName;
		this.dataType = dataType;
		this.columnSize = columnSize;
	}
}
