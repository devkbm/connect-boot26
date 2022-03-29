package com.like.hrm.duty.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.like.hrm.duty.domain.model.vo.FamilyEvent;
import com.like.system.core.domain.AuditEntity;
import com.like.system.core.vo.LocalDatePeriod;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Aggregation Root
 *  
 * 근태시작일시 ~ 종료일시의 시간이 포함되려면 연속된 근태여야 함 </br>
 * 연속된 근태가 아닐 경우 DutyApplicationDate에서 구분이 불가능
 *  
 * @author CB457 
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMDUTYAPPLICATION")
public class DutyApplication extends AuditEntity {
	
	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DUTY_ID", nullable = false)
	private Long id;
	
	@Column(name="STAFF_ID", nullable = false)
	private String staffId;
	
	@Column(name="DUTY_CODE", nullable = false)
	private String dutyCode;
	
	@Column(name="DUTY_REASON", nullable = false)
	private String dutyReason;	
		
	@AttributeOverrides({
		@AttributeOverride(name = "from", column = @Column(name = "DUTY_FROM_DT")),
		@AttributeOverride(name = "to", column = @Column(name = "DUTY_TO_DT"))
	})
	LocalDatePeriod period;
		
	@OneToMany(mappedBy = "dutyApplication", orphanRemoval = true, cascade = CascadeType.ALL)
	List<DutyApplicationDate> selectedDate;
	
	@Embedded
	FamilyEvent familyEvent;
	
	@Transient
	private List<DutyApplicationAttachedFile> fileList;
	
	public static DutyApplication of(String staffId
									,String dutyCode
									,String dutyReason
									,LocalDatePeriod period
									,List<LocalDate> selectedDate
									,Double dutyTime) {
		
		DutyApplication newObj = new DutyApplication();
		newObj.staffId = staffId;
		newObj.dutyCode = dutyCode;
		newObj.dutyReason = dutyReason;
		newObj.period = period;		
		
		return newObj;
	}
		
	public DutyApplication(String staffId
						  ,String dutyCode
						  ,String dutyReason
						  ,LocalDatePeriod period						  
						  ,List<LocalDate> selectedDate
						  ,Double dutyTime) {
		this.staffId = staffId;
		this.dutyCode = dutyCode;
		this.dutyReason = dutyReason;
		this.period = period;		
		this.selectedDate = addApplicationDate(selectedDate, dutyTime);
	}	
	
	public void modifyEntity(String dutyCode
							,String dutyReason
							,LocalDatePeriod period
							,List<LocalDate> selectedDate) {
		this.dutyCode = dutyCode;
		this.dutyReason = dutyReason;
		this.period = period;
		
		this.selectedDate.clear();
		this.selectedDate = addApplicationDate(selectedDate, 8);
	}
	
	public void addFile(DutyApplicationAttachedFile file) {
		this.fileList.add(file);
	}
	
	public List<LocalDate> getSelectedDate() {
		return this.selectedDate.stream().map(e -> e.getDate()).collect(Collectors.toList());
	}
	
	private List<DutyApplicationDate> addApplicationDate(List<LocalDate> dateList, double dutyTime) {
		if (this.selectedDate == null)
			this.selectedDate = new ArrayList<>();
		
		for (LocalDate date : dateList) {
			this.selectedDate.add(new DutyApplicationDate(this, date, dutyTime));
		}		
		
		return this.selectedDate;
	}
	
}
