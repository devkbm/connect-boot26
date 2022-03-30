package com.like.hrm.duty.domain.model;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.like.system.core.vo.LocalDatePeriod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DutyApplicationTest {

	@DisplayName("객체 생성 테스트")
	@Test
	void createEntity() {
		// Given 			
		// When
		DutyApplication dutyApplication = createEntity(LocalDate.of(2022, 1, 15), LocalDate.of(2022, 1, 16), new BigDecimal("8"));
						
		// Then
		assertThat(dutyApplication.getStaffId()).isEqualTo("staffId");
		assertThat(dutyApplication.getDutyCode()).isEqualTo("연차코드");
		assertThat(dutyApplication.getDutyReason()).isEqualTo("test");
		assertThat(dutyApplication.getPeriod().getFrom()).isEqualTo(LocalDate.of(2022, 1, 15));
		assertThat(dutyApplication.getPeriod().getTo()).isEqualTo(LocalDate.of(2022, 1, 16));						
		assertThat(dutyApplication.getSelectedDate().get(0)).isEqualTo(LocalDate.of(2022, 1, 15));
		assertThat(dutyApplication.getSelectedDate().get(1)).isEqualTo(LocalDate.of(2022, 1, 16));
		assertThat(dutyApplication.getSumDutyTime()).isEqualTo(new BigDecimal("16"));
		//log.info(dutyApplication.toString());					
	}
	
	@DisplayName("객체 수정 테스트")
	@Test
	void modifyEntity() {
		// Given
		DutyApplication dutyApplication = createEntity(LocalDate.of(2022, 1, 15), LocalDate.of(2022, 1, 16), new BigDecimal("8"));
		
		LocalDatePeriod modifyPeriod = new LocalDatePeriod(LocalDate.of(2022, 1, 25), LocalDate.of(2022, 1, 26));
		
		// When
		dutyApplication.modifyEntity("반차코드", "test2", modifyPeriod, List.of(LocalDate.of(2022, 1, 25), LocalDate.of(2022, 1, 26)), new BigDecimal("4"));
		
		// Then 
		assertThat(dutyApplication.getStaffId()).isEqualTo("staffId");
		assertThat(dutyApplication.getDutyCode()).isEqualTo("반차코드");
		assertThat(dutyApplication.getDutyReason()).isEqualTo("test2");
		assertThat(dutyApplication.getPeriod().getFrom()).isEqualTo(LocalDate.of(2022, 1, 25));
		assertThat(dutyApplication.getPeriod().getTo()).isEqualTo(LocalDate.of(2022, 1, 26));						
		assertThat(dutyApplication.getSelectedDate().get(0)).isEqualTo(LocalDate.of(2022, 1, 25));
		assertThat(dutyApplication.getSelectedDate().get(1)).isEqualTo(LocalDate.of(2022, 1, 26));
		assertThat(dutyApplication.getSumDutyTime()).isEqualTo(new BigDecimal("8"));
		//log.info(dutyApplication.toString());		
	}
	
	private DutyApplication createEntity(LocalDate from, LocalDate to, BigDecimal dutyTime) {
		LocalDatePeriod period = new LocalDatePeriod(from, to);

		List<LocalDate> dateList = List.of(from, to);

		return new DutyApplication("staffId", "연차코드", "test", period, dateList, dutyTime);
	}
	
}
