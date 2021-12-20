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
import com.like.system.core.vo.Period;

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
@Builder(builderClassName = "dtoBuilder", builderMethodName = "dtoBuilder")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMDUTYAPPLICATION")
public class DutyApplication extends AuditEntity {
	
	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DUTY_ID", nullable = false)
	private Long dutyId;
	
	@Column(name="EMP_ID", nullable = false)
	private String employeeId;
	
	@Column(name="DUTY_CODE", nullable = false)
	private String dutyCode;
	
	@Column(name="DUTY_REASON", nullable = false)
	private String dutyReason;	
		
	@AttributeOverrides({
		@AttributeOverride(name = "from", column = @Column(name = "DUTY_START_DT")),
		@AttributeOverride(name = "to", column = @Column(name = "DUTY_END_DT"))
	})
	Period period;
		
	@OneToMany(mappedBy = "dutyApplication", orphanRemoval = true, cascade = CascadeType.ALL)
	List<DutyApplicationDate> selectedDate;
	
	@Embedded
	FamilyEvent familyEvent;
	
	@Transient
	private List<DutyApplicationAttachedFile> fileList;
	
	@Builder
	public DutyApplication(String employeeId
						  ,String dutyCode
						  ,String dutyReason
						  ,Period period
						  ,FamilyEvent familyEvent
						  ,List<LocalDate> selectedDate) {
		this.employeeId = employeeId;
		this.dutyCode = dutyCode;
		this.dutyReason = dutyReason;
		this.period = period;
		this.familyEvent = familyEvent;
		this.selectedDate = addApplicationDate(selectedDate);
	}	
	
	public void modifyEntity(String dutyCode
							,String dutyReason
							,Period period
							,List<LocalDate> selectedDate) {
		this.dutyCode = dutyCode;
		this.dutyReason = dutyReason;
		this.period = period;
		
		this.selectedDate.clear();
		this.selectedDate = addApplicationDate(selectedDate);
	}
	
	public void addFile(DutyApplicationAttachedFile file) {
		this.fileList.add(file);
	}
	
	public List<LocalDate> getSelectedDate() {
		return this.selectedDate.stream().map(e -> e.getDate()).collect(Collectors.toList());
	}
	
	private List<DutyApplicationDate> addApplicationDate(List<LocalDate> dateList) {
		if (this.selectedDate == null)
			this.selectedDate = new ArrayList<>();
		
		for (LocalDate date : dateList) {
			this.selectedDate.add(new DutyApplicationDate(this, date));
		}		
		
		return this.selectedDate;
	}
	
}
