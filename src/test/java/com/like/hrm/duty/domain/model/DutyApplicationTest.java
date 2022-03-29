package com.like.hrm.duty.domain.model;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.like.system.core.vo.LocalDatePeriod;

public class DutyApplicationTest {

	@DisplayName("객체 생성 테스트")
	@Test
	public void createObject() {
		// Given 
		LocalDatePeriod period = new LocalDatePeriod(LocalDate.of(2022, 1, 15)
								  					,LocalDate.of(2022, 1, 16));
						 	
		
		List<LocalDate> dateList = List.of(LocalDate.of(2022, 1, 15)
										  ,LocalDate.of(2022, 1, 16));
		
		// When
		DutyApplication dutyApplication = new DutyApplication("staffId", "연차코드", "test", period, dateList, 8d);
						
		// Then
		assertThat(dutyApplication.getStaffId()).isEqualTo("staffId");
		assertThat(dutyApplication.getDutyCode()).isEqualTo("연차코드");
		assertThat(dutyApplication.getDutyReason()).isEqualTo("test");
		assertThat(dutyApplication.getPeriod().getFrom()).isEqualTo(LocalDate.of(2022, 1, 15));
		assertThat(dutyApplication.getPeriod().getTo()).isEqualTo(LocalDate.of(2022, 1, 16));						
		
	}
	
}
