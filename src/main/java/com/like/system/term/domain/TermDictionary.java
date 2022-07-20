package com.like.system.term.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>용어사전 엔티티<p/>
 * 
 * 1. 단일단어로 구성 <br>
 * 2. 복합단어로 구성 <br>
 *   - 복합단어일 경우 _(under-bar)로 결합 <br>
 */
@Getter
@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "COMTERM")
@EntityListeners(AuditingEntityListener.class)
public class TermDictionary extends AbstractAuditEntity {	

	@Id	
	@Column(name="TERM_ID")
	String id;	
	
	@Column(name="SYSTEM")
	String system;
	
	@Column(name="TERM", unique = true)
	String term;		
	
	@Column(name="TERM_ENG")
	String termEng;
		
	@Column(name="PHYSICAL_NAME")
	String physicalName;
	
	@Column(name="DESCRIPTION")
	String description;	
	
	@Column(name="CMT")
	String comment;

	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}  )
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id")
	DomainDictionary domainId;
	
	@Builder
	public TermDictionary(String system, String term, String termEng,
			String physicalName, DomainDictionary termDomain, String description, String comment) {
		this.id = system + term;
		this.system = system;
		this.term = term;		
		this.termEng = termEng;
		this.domainId = termDomain; 
		this.physicalName = physicalName;
		this.description = description;
		this.comment = comment;
	}	
	
	public TermDictionary of(String system, WordDictionary word, DomainDictionary termDomain, String description, String comment) {	
		return TermDictionary.builder()
							 .system(system)
							 .term(word.getLogicalName())
							 .termEng(word.getLogicalNameEng())
							 .termDomain(termDomain)
							 .physicalName(word.getPhysicalName())
							 .description(description)
							 .comment(comment)
							 .build();
	}
	
	public TermDictionary of(String system, List<WordDictionary> wordList, DomainDictionary termDomain, String description, String comment) {
		String logicalName = String.join("_", wordList.stream().map(e -> e.getLogicalName()).toList());
		String logicalNameEng = String.join("_", wordList.stream().map(e -> e.getLogicalNameEng()).toList());
		String physicalName = String.join("_", wordList.stream().map(e -> e.getPhysicalName()).toList());					
		
		return TermDictionary.builder()
							 .system(system)
							 .term(logicalName)
							 .termEng(logicalNameEng)
							 .termDomain(termDomain)
							 .physicalName(physicalName)
							 .description(description)
							 .comment(comment)
							 .build();
	}
	
	
	public void modifyEntity(String nameEng
							,String physicalName
							,String description
							,String comment) {			
		this.termEng = nameEng;
		this.physicalName = physicalName;
		this.description = description;
		this.comment = comment;
	}
	
}
