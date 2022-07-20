package com.like.system.term.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
public class TermDictionaryRepositoryTest { 

	@Autowired
	private TermDictionaryRepository repository;
	
	@WithMockCustomUser
	@DisplayName("용어사전 저장")
	@Test
	void saveTermDictionary1() {
		//Given
		List<WordDictionary> wordList = createSystemWordList(); 			
		
		//When
		//repository.saveAndFlush(entity);		
		WordDictionary result = null;					
		
		log.info(result.toString());
		
		//Then
		assertThat(result.getLogicalName()).isEqualTo("코드");
		assertThat(result.getLogicalNameEng()).isEqualTo("CODE");
		assertThat(result.getPhysicalName()).isEqualTo("CD");		
	}
	
	List<WordDictionary> createSystemWordList() {
		return List.of(new WordDictionary("직원", "STAFF", "STF")
					  ,new WordDictionary("번호", "NUMBER", "NO"));
	}
}
