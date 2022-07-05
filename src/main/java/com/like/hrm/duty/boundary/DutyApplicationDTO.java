package com.like.hrm.duty.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.model.QDutyApplication;
import com.like.system.core.jpa.vo.LocalDatePeriod;
import com.like.system.holiday.domain.service.DateInfo;
import com.like.system.holiday.domain.service.DateInfoList;
import com.like.system.holiday.service.DateInfoService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DutyApplicationDTO {

	public record Search(
			String staffId
			) {
		private static QDutyApplication qDutyApplication = QDutyApplication.dutyApplication;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqStaffId(this.staffId));
			
			return builder;
		}
		
		private BooleanExpression eqStaffId(String staffId) {
			return hasText(staffId) ? qDutyApplication.staffId.eq(staffId) : null;					
		}
	}	
		
	public record SaveDutyApplication(
			Long dutyId,
			String staffId,
			String dutyCode,
			String dutyReason,
			LocalDate fromDate,
			LocalDate toDate,
			List<DutyDate> selectedDate,
			BigDecimal dutyTime) {
		
		public static SaveDutyApplication convert(DutyApplication e, DateInfoService service) {								
			DateInfoList dateInfoList = service.getDateInfoList(e.getPeriod().getFrom()
															   ,e.getPeriod().getTo());
								
			return new SaveDutyApplication(e.getId()
										  ,e.getStaffId()
										  ,e.getDutyCode()
										  ,e.getDutyReason()
										  ,e.getPeriod().getFrom()
										  ,e.getPeriod().getTo()
										  ,SaveDutyApplication.convertDutyDate(e, dateInfoList)
										  ,e.getSumDutyTime()); 
									  									  									  									 
		}
		
		public DutyApplication newEntity() {		
			
			return new DutyApplication(staffId								  
								      ,dutyCode
								      ,dutyReason
								      ,new LocalDatePeriod(fromDate, toDate)
								      ,this.getSelectedDate()
								      ,dutyTime);
			
		}
		
		public void modifyEntity(DutyApplication entity) {
			entity.modifyEntity(dutyCode
							   ,dutyReason
							   ,new LocalDatePeriod(fromDate, toDate)
							   ,this.getSelectedDate()
							   ,dutyTime);		
		}			
		
		private List<LocalDate> getSelectedDate() {
			return selectedDate.stream().map(e -> e.date()).toList();
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
	
	public record DutyDate(
			LocalDate date,
			@JsonProperty("isSelected")boolean isSelected,
			@JsonProperty("isHoliday")boolean isHoliday
			) {
		
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
