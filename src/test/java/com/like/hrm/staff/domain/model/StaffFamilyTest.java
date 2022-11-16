package com.like.hrm.staff.domain.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.like.hrm.staff.domain.model.family.StaffFamily;

@DisplayName("직원 가족 등록 Test")
public class StaffFamilyTest {

	@DisplayName("객체 생성 테스트")
	@Test
	public void 가족등록() {
		Staff staff = createStaff();
		
		StaffFamily family1 = new StaffFamily(staff, "직원가족명1", "주민등록번호", "부", "직업", "학력", "비고");
		
		staff.getFamilyList().add(family1);
		
		assertThat(staff.getId()).isEqualTo("001_2002");
	}
	
	public Staff createStaff() {
		return new Staff("001"
		                ,() -> "2002"
		                ,new StaffName("한글명", "영문명", "한문명")		                
		                ,"9912011111111");
	}
}
