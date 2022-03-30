package com.like.hrm.duty.boundary;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.model.QDutyApplication;
import com.like.system.core.vo.LocalDatePeriod;
import com.like.system.core.vo.Period;
import com.like.system.holiday.domain.service.DateInfo;
import com.like.system.holiday.domain.service.DateInfoList;
import com.like.system.holiday.service.DateInfoService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DutyApplicationDTO {

	@Builder
	public static class SearchDutyApplication implements Serializable {
		
		private static final long serialVersionUID = 6850895780318962483L;
		
		private final QDutyApplication qDutyApplication = QDutyApplication.dutyApplication;
		
		String staffId;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqStaffId(this.staffId));
			
			return builder;
		}
		
		private BooleanExpression eqStaffId(String staffId) {
			if (!StringUtils.hasText(staffId)) return null;
						
			return qDutyApplication.staffId.eq(staffId);
		}
		
	}
	
	@JsonAutoDetect(fieldVisibility = Visibility.ANY)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveDutyApplication implements Serializable {
				
		private static final long serialVersionUID = -6474584560142236686L;

		private Long dutyId;
				
		private String staffId;
				
		private String dutyCode;
				
		private String dutyReason;
						
		private LocalDate dutyStartDateTime;
				
		private LocalDate dutyEndDateTime;
		
		private List<DutyDate> selectedDate;
		
		public DutyApplication newEntity() {		
			return null;
			/*
			return DutyApplication.builder()								  
								  .staffId(staffId)
								  .dutyCode(dutyCode)
								  .dutyReason(dutyReason)
								  .period(new Period(dutyStartDateTime, dutyEndDateTime))
								  .selectedDate(this.getSelectedDate())
								  .build();
								  */
		}
		
		public void modifyEntity(DutyApplication entity) {
			entity.modifyEntity(dutyCode
							   ,dutyReason
							   ,new LocalDatePeriod(dutyStartDateTime, dutyEndDateTime)
							   ,this.getSelectedDate()
							   ,new BigDecimal("8"));		
		}
		
		public static SaveDutyApplication convert(DutyApplication entity, DateInfoService service) {					
			
			DateInfoList dateInfoList = service.getDateInfoList(entity.getPeriod().getFrom()
															   ,entity.getPeriod().getTo());
			
			return SaveDutyApplication.builder()
									  .dutyId(entity.getId())
									  .staffId(entity.getStaffId())
									  .dutyCode(entity.getDutyCode())
									  .dutyReason(entity.getDutyReason())
									  .dutyStartDateTime(entity.getPeriod().getFrom())
									  .dutyEndDateTime(entity.getPeriod().getTo())
									  .selectedDate(SaveDutyApplication.convertDutyDate(entity, dateInfoList))
									  .build();
		}
		
		private List<LocalDate> getSelectedDate() {
			return selectedDate.stream().map(e -> e.getDate()).collect(Collectors.toList());
		}
		
		private static List<DutyDate> convertDutyDate(DutyApplication entity, DateInfoList dateInfoList) {
			List<DutyDate> dutyDatelist = new ArrayList<>(dateInfoList.size());
			List<LocalDate> selectedDate = entity.getSelectedDate();					
			
			for (DateInfo date : dateInfoList.getDates()) {							
				dutyDatelist.add(new DutyDate(date.getDate()										
											 ,selectedDate.contains(date.getDate())
											 ,date.isHoliday()));
			}
			
			log.info(dutyDatelist.toString());
			
			return dutyDatelist;			
		}
					
	}
		
	
	@JsonAutoDetect(isGetterVisibility = Visibility.ANY)	
	@ToString(includeFieldNames = true)
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DutyDate implements Serializable {
				
		private static final long serialVersionUID = 5742215979107207193L;

		LocalDate date;
		
		@JsonProperty(value = "isSelected")
		boolean isSelected;
		
		@JsonProperty(value = "isHoliday")
		boolean isHoliday;
		
		public static List<DutyDate> convertDutyDate(DateInfoList dateInfoList) {
			List<DutyDate> dutyDatelist = new ArrayList<>(dateInfoList.size());
			
			for (DateInfo date : dateInfoList.getDates()) {							
				dutyDatelist.add(new DutyDate(date.getDate()										
											 ,true
											 ,date.isHoliday()));
			}					
			
			return dutyDatelist;
		}
	}
}
