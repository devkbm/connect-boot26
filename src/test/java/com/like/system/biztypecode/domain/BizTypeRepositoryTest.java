package com.like.system.biztypecode.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class BizTypeRepositoryTest {

	@Autowired
	private BizTypeRepository repository;

	@DisplayName("업무구분 저장시 ID가 NULL이면 오류")
	@Test
	void saveBizTypeCode_ERROR() {			
			
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> { 
			//new BizTypeCode(null, "테스트", BizTypeEnum.HRM, "비고"); 
		});	
	}
	
	@DisplayName("업무구분 저장")
	@Test
	void saveBizTypeCode() {
		//Given
		BizType bizType = null;// new BizTypeCode("TEST", "테스트", BizTypeEnum.HRM, "비고");
		
		//When
		repository.saveAndFlush(bizType);
				
		log.info(bizType.toString());
		
		//Then
		assertThat(bizType.getId()).isEqualTo("TEST");
		assertThat(bizType.getName()).isEqualTo("테스트");
		assertThat(bizType.getUseYn()).isEqualTo(true);
		assertThat(bizType.getSequence()).isEqualTo(0);
		assertThat(bizType.getBizType()).isEqualTo(BizTypeEnum.HRM);
		assertThat(bizType.getComment()).isEqualTo("비고");		
	}
	
	@DisplayName("업무상세코드 저장시 ID가 NULL이면 오류")
	@Test
	void saveBizDetailCode_ERROR() {			
			
		assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> { 
			new BizTypeCode(null, "CODE", "이름", "비고");
		});
		
	}
}
