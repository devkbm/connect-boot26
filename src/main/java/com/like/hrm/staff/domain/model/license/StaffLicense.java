package com.like.hrm.staff.domain.model.license;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.hrm.staff.domain.model.Staff;
import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>자격 면허 관리</p>
 * 
 * Unique Index : EMP_ID, DEPT_TYPE, DEPT_CODE <br>
 * [상세] <br>
 * 1. <br>
 * 2. <br>
 * @author 김병민
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = {"staff"})
@EqualsAndHashCode(callSuper = false, of = {"id"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMSTAFFLICENSE")
@EntityListeners(AuditingEntityListener.class)
public class StaffLicense extends AbstractAuditEntity implements Serializable {
	
	private static final long serialVersionUID = 5879415854289672377L;
	
	// 취득일자, 자격면허, 자격면허인가번호, 발행기관, 필수면허번호여부, 이미지		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STAFF_ID", nullable=false, updatable=false, insertable = false)
	Staff staff;
	
	@EmbeddedId
	StaffLicenseId id;
			
	@Comment("자격면허유형")
	@Column(name="LICENSE_TYPE", nullable = false)
	String licenseType;
		
	@Comment("자격면허번호")
	@Column(name="LICENSE_NO", nullable = false)
	String licenseNumber;
		
	// acquisition date
	@Comment("취득일자")
	@Column(name="DATE_OF_ACQUISITION", nullable = true)
	LocalDate dateOfAcquisition;
		
	@Comment("인증기관")
	@Column(name="CERTIFICATION_AUTHORITY", nullable = true)
	String certificationAuthority;		
		
	@Comment("비고")
	@Column(name="CMT", nullable = true)
	String comment;	
	
	@Builder
	public StaffLicense(Staff staff
					   ,String licenseType
					   ,String licenseNumber
					   ,LocalDate dateOfAcquisition
					   ,String certificationAuthority
					   ,String comment) {
		this.staff = staff;
		this.id = new StaffLicenseId(staff, staff.getLicenseList().getNextSequence());
		this.licenseType = licenseType;
		this.licenseNumber = licenseNumber;
		this.dateOfAcquisition = dateOfAcquisition;
		this.certificationAuthority = certificationAuthority;
		this.comment = comment;
	}
	
	@Builder(builderMethodName = "modifyBuilder", buildMethodName = "modify")
	public void modifyEntity(String licenseType
							,String licenseNumber
							,LocalDate dateOfAcquisition
							,String certificationAuthority
							,String comment) {
		this.licenseType = licenseType;
		this.licenseNumber = licenseNumber;
		this.dateOfAcquisition = dateOfAcquisition;
		this.certificationAuthority = certificationAuthority;
		this.comment = comment;		
	}
				
}
