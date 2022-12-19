package com.like.hrm.staff.domain.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.like.hrm.staff.boundary.StaffDTO;

@DisplayName("직원번호 생성 Test")
public class StaffTest {

	@Nested
	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	class 직원번호는 {
	
		@Nested
		@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
		class 수동_생성인_경우 {
			
			@Test
			@DisplayName("직원번호를 String으로 입력한다.")	
			void 직원번호를_직접_입력한다() {								
				Staff staff = new Staff("001"
						               ,() -> "2002"
						               ,StaffName.of("한글명", "영문명", "한문명")
						               ,"9912011111111");
				// Then
				assertThat(staff.getId()).isEqualTo("001_2002");
				assertThat(staff.getStaffNo()).isEqualTo("2002");
				assertThat(staff.getName().getName()).isEqualTo("한글명");
				assertThat(staff.getName().getNameEng()).isEqualTo("영문명");
				assertThat(staff.getName().getNameChi()).isEqualTo("한문명");
				assertThat(staff.getResidentRegistrationNumber().getNumber()).isEqualTo("9912011111111");
			}
			
			@Test
			@DisplayName("직원번호를 DTO로 입력한다.")	
			void 직원번호를_DTO로_입력한다() {										
				StaffDTO.NewStaff dto = new StaffDTO.NewStaff("appUrl", "001", "2002", "한글명", "영문명", "한문명", "9912011111111");
				
				StaffNoCreateStrategy strategy = () -> dto.staffNo();
				
				Staff staff = new Staff("001"
						               ,strategy
						               ,StaffName.of("한글명", "영문명", "한문명")
						               ,"9912011111111");
				// Then
				assertThat(staff.getId()).isEqualTo("001_2002");
				assertThat(staff.getStaffNo()).isEqualTo("2002");
				assertThat(staff.getName().getName()).isEqualTo("한글명");
				assertThat(staff.getName().getNameEng()).isEqualTo("영문명");
				assertThat(staff.getName().getNameChi()).isEqualTo("한문명");
				assertThat(staff.getResidentRegistrationNumber().getNumber()).isEqualTo("9912011111111");
			}
			
		}
	}
		
	
	@DisplayName("주민번호 입력형식 예외 테스트")
	@Test
	void createStaffException() {						
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
			StaffNoCreateStrategy strategy = () -> "2002";
			
			new Staff("001", strategy, StaffName.of("한글명", "영문명", "한문명"), "99120111111112");			
		});							
	}
	
	
	
}
