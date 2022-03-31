package com.like.hrm.staff.domain.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StaffTest {

	@DisplayName("직원번호 생성")
	@Test
	void createStaff() {
		// Given 			
		// When
		Staff staff = Staff.of("staffId", new StaffName("직원이름","staffname","한자"), "9912011111111");
		
		// Then
		assertThat(staff.getId()).isEqualTo("staffId");
		assertThat(staff.getName().getName()).isEqualTo("직원이름");
		assertThat(staff.getName().getNameEng()).isEqualTo("staffname");
		assertThat(staff.getName().getNameChi()).isEqualTo("한자");
		assertThat(staff.getResidentRegistrationNumber().getNumber()).isEqualTo("9912011111111");
	}
	
	@DisplayName("주민번호 입력형식 예외")
	@Test
	void createStaffException() {		
		// Given 			
		// When		
		//Throwable thrown = catchThrowable(() -> { Staff.of("staffId", new StaffName("직원이름","staffname","한자"), "99120111111112"); });
		
		// Then
		//assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
				
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> { 
			Staff.of("staffId", new StaffName("직원이름","staffname","한자"), "99120111111112"); 
		});							
	}
	
	
	
}
