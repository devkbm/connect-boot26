package com.like.system.term.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.like.system.core.test.WithMockCustomUser;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class SystemWordRepositoryTest {

	@Autowired
	private SystemWordRepository repository;

	
	@DisplayName("저장시 ID가 NULL이면 오류")
	@Test
	void saveSystemWord_ERROR() {			
			
		assertThatExceptionOfType(NullPointerException .class).isThrownBy(() -> { 
			new SystemWord(null, "TEST2");
		});	
	}
	
	@WithMockCustomUser
	@DisplayName("시스템단어 저장")
	@Test
	void saveSystemWord() {
		//Given
		SystemWord entity = new SystemWord("코드", "CD");			
		
		//When
		repository.save(entity);		
		SystemWord result = repository.findById("코드").orElse(null);					
		
		//Then
		assertThat(result.getLogicalName()).isEqualTo("코드");
		assertThat(result.getPhysicalName()).isEqualTo("CD");		
	}
	
	
}
