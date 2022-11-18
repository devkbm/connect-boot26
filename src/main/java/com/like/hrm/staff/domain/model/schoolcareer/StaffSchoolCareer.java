package com.like.hrm.staff.domain.model.schoolcareer;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
import com.like.system.core.jpa.vo.LocalDatePeriod;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>부서 이력 관리 클래스</p>
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
@Table(name = "HRMSTAFFSCHOOLCAREER")
@EntityListeners(AuditingEntityListener.class)
public class StaffSchoolCareer extends AbstractAuditEntity implements Serializable {
	
	private static final long serialVersionUID = 5879415854289672377L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STAFF_ID", nullable=false, updatable=false, insertable = false)
	private Staff staff;

	@EmbeddedId
	StaffSchoolCareerId id;
			
	@Comment("학력유형")
	@Column(name="SCHOOL_CAREER_CODE")
	String schoolCareerType;
		
	@Comment("학교코드")
	@Column(name="SCHOOL_CODE")
	String schoolCode;
		
	@Embedded
	LocalDatePeriod period;
		
	@Comment("전공학과명")
	@Column(name="MAJOR_NAME")
	String majorName;
		
	@Comment("복수전공학과명")
	@Column(name="PLURAL_MAJOR_NAME")
	String pluralMajorName;
		
	@Comment("소재지")
	@Column(name="LOCATION_NAME")
	String location;
		
	@Comment("수업연한")
	@Column(name="LESSON_YEAR")
	Integer lessonYear;
		
	@Comment("비고")
	@Column(name="CMT")
	String comment;
		
	// 시작일, 종료일, 전공학과명, 복수전공학과명, 학교소재지, 수업연한, 입사학력여부, 수고권대학여부, 야간여부, 이공계여부, 이미지	
	
	@Builder
	public StaffSchoolCareer(Staff staff
						    ,String schoolCareerType
						    ,String schoolCode
						    ,LocalDate fromDate
							,LocalDate toDate
							,String majorName
							,String pluralMajorName
							,String location
							,Integer lessonYear
						    ,String comment) {
		this.staff = staff;
		this.id = new StaffSchoolCareerId(staff, staff.getSchoolCareerList().getNextSequence());		
		this.schoolCareerType = schoolCareerType;
		this.schoolCode = schoolCode;
		this.period = new LocalDatePeriod(fromDate, toDate);
		this.majorName = majorName;
		this.pluralMajorName = pluralMajorName;
		this.location = location;
		this.lessonYear = lessonYear;
		this.comment = comment;
	}
		
	@Builder(builderMethodName = "modifyBuilder", buildMethodName = "modify")
	public void modifyEntity(
			String schoolCareerType
		    ,String schoolCode
		    ,LocalDate fromDate
			,LocalDate toDate
			,String majorName
			,String pluralMajorName
			,String location
			,Integer lessonYear
		    ,String comment) {
		this.schoolCareerType = schoolCareerType;
		this.schoolCode = schoolCode;
		this.period = new LocalDatePeriod(fromDate, toDate);
		this.majorName = majorName;
		this.pluralMajorName = pluralMajorName;
		this.location = location;
		this.lessonYear = lessonYear;
		this.comment = comment;
	}
				
}
