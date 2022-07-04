package com.like.system.dept.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import com.like.system.core.jpa.domain.AbstractAuditEntity;
import com.like.system.core.jpa.vo.LocalDatePeriod;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true, includeFieldNames = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "internalBuilder")
@Getter
@Entity
@Table(name = "comdept")
@EntityListeners(AuditingEntityListener.class)
public class Dept extends AbstractAuditEntity implements Serializable {

	private static final long serialVersionUID = -969524977226888898L;

	@Id
	@Column(name = "DEPT_ID", nullable = false)
	String deptId;
	
	@Column(name = "ORG_CD", nullable = false)
	String organizationCode;
	
	@Column(name = "DEPT_CD", nullable = false)
	String deptCode;

	@Column(name = "DEPT_NM_KOR")
	String deptNameKorean;

	@Column(name = "DEPT_ABBR_KOR")
	String deptAbbreviationKorean;

	@Column(name = "DEPT_NM_ENG")
	String deptNameEnglish;

	@Column(name = "DEPT_ABBR_ENG")
	String deptAbbreviationEnglish;
	
	@Embedded
	LocalDatePeriod period;
	
	@Builder.Default
	@Column(name="PRT_SEQ")
	int seq = 0;
	
	@Column(name = "CMT")
	String comment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "P_DEPT_ID", nullable = true)
	Dept parentDept;
	
	public static DeptBuilder builder(String organizationCode, String deptCode) {
		Assert.hasText(organizationCode, "organizationCode must not be empty!");
		Assert.hasText(deptCode, "deptCode must not be empty!");
		
		return internalBuilder().deptId(organizationCode + deptCode)
								.organizationCode(organizationCode)
								.deptCode(deptCode);
	}	

	/**
	 * @param deptNameKorean
	 * @param deptAbbreviationKorean
	 * @param deptNameEnglish
	 * @param deptAbbreviationEnglish
	 * @param fromDate
	 * @param toDate
	 * @param seq
	 * @param comment
	 * @param parentDept
	 */
	public void modifyEntity(String deptNameKorean
							,String deptAbbreviationKorean
							,String deptNameEnglish
							,String deptAbbreviationEnglish
							,LocalDatePeriod period
							,int seq
							,String comment
							,Dept parentDept) {
		this.deptNameKorean = deptNameKorean;
		this.deptAbbreviationKorean = deptAbbreviationKorean;
		this.deptNameEnglish = deptNameEnglish;
		this.deptAbbreviationEnglish = deptAbbreviationEnglish;
		this.period = period;
		this.seq = seq;
		this.comment = comment;
		this.parentDept = parentDept;
	}	
	
	public Dept getParentDept() {
		return parentDept;
	}
}
