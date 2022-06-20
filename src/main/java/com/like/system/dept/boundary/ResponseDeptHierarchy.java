package com.like.system.dept.boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.system.core.jpa.vo.LocalDatePeriod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDeptHierarchy {

	LocalDateTime createdDt;	
	
	String createdBy;
	
	LocalDateTime modifiedDt;
	
	String modifiedBy;
	
	String parentDeptCode;
			
	String deptCode;		
			
	String deptNameKorean;		
	
	String deptAbbreviationKorean;
	
	String deptNameEnglish;
	
	String deptAbbreviationEnglish;
						
	LocalDate fromDate;
			
	LocalDate toDate;
	
	Integer seq;
	
	String comment;
	
	@Singular
	List<ResponseDeptHierarchy> children;
	
	/**
	 * NzTreeNode property 
	 */
	String title;
	
	String key;
			
	@JsonProperty(value="isLeaf") 
	boolean isLeaf;

	public ResponseDeptHierarchy(LocalDateTime createdDt, String createdBy, LocalDateTime modifiedDt, String modifiedBy,
			String parentDeptCode, String deptCode, String deptNameKorean, String deptAbbreviationKorean,
			String deptNameEnglish, String deptAbbreviationEnglish, LocalDatePeriod period,
			Integer seq, String comment) {		
		this.createdDt = createdDt;
		this.createdBy = createdBy;
		this.modifiedDt = modifiedDt;
		this.modifiedBy = modifiedBy;
		this.parentDeptCode = parentDeptCode;
		this.deptCode = deptCode;
		this.deptNameKorean = deptNameKorean;
		this.deptAbbreviationKorean = deptAbbreviationKorean;
		this.deptNameEnglish = deptNameEnglish;
		this.deptAbbreviationEnglish = deptAbbreviationEnglish;
		this.fromDate = period.getFrom();
		this.toDate = period.getTo();
		this.seq = seq;
		this.comment = comment;
		
		this.title 	= this.deptNameKorean;
		this.key 	= this.deptCode;			
	}

	public void setChildren(List<ResponseDeptHierarchy> children) {
		this.children = children;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
