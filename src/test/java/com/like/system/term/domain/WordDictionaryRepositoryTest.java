package com.like.system.term.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.like.system.core.jpa.JpaRepositoryAuditConfigTest;
import com.like.system.core.test.WithMockCustomUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Import(JpaRepositoryAuditConfigTest.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class WordDictionaryRepositoryTest {

	@Autowired
	private WordDictionaryRepository repository;

	
	@DisplayName("저장시 ID가 NULL이면 오류")
	@Test
	void saveWordDictionary_ERROR() {			
			
		assertThatExceptionOfType(NullPointerException .class).isThrownBy(() -> { 
			new WordDictionary(null, "NULL", "TEST2", "비고");
		});	
	}
	
	@WithMockCustomUser
	@DisplayName("시스템단어 저장")
	@Test
	void saveWordDictionary() {
		//Given
		WordDictionary entity = new WordDictionary("코드", "CODE", "CD", "비고");			
		
		//When
		repository.saveAndFlush(entity);		
		WordDictionary result = repository.findById("코드").orElse(null);					
		
		log.info(result.toString());
		
		//Then
		assertThat(result.getLogicalName()).isEqualTo("코드");
		assertThat(result.getLogicalNameEng()).isEqualTo("CODE");
		assertThat(result.getPhysicalName()).isEqualTo("CD");		
		assertThat(result.getComment()).isEqualTo("비고");
	}
	
	
}
