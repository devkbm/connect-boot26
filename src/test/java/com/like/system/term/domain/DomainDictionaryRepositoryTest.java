package com.like.system.term.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.like.system.term.domain.DomainDictionary.Database;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class DomainDictionaryRepositoryTest {

	@Autowired
	private DomainDictionaryRepository repository;

	
	@DisplayName("저장시 ID가 NULL이면 오류")
	@Test
	void saveSystemWord_ERROR() {			
			
		assertThatExceptionOfType(NullPointerException .class).isThrownBy(() -> { 
			//new SystemWord(null, "TEST2");
		});	
	}
	
		
	@DisplayName("단어 리스트로 용어 생성")
	@Test
	void saveTermDomain() {
		//Given
		// Database database, String domainName, String dataType, String columnSize
		DomainDictionary entity = DomainDictionary.builder()
									  .database(Database.MYSQL)
									  .domainName("직원번호")
									  .dataType("VARCHAR")
									  .columnSize("8")
									  .build();			
		
		//When
		repository.save(entity);		
		DomainDictionary result = repository.findById("MYSQL_직원번호").orElse(null);					
		
		//Then
		assertThat(result.getId()).isEqualTo("MYSQL_직원번호");
		assertThat(result.getDatabase()).isEqualTo(Database.MYSQL);
		assertThat(result.getDomainName()).isEqualTo("직원번호");		
		assertThat(result.getDataType()).isEqualTo("VARCHAR");
		assertThat(result.getColumnSize()).isEqualTo("8");
	}
	
	List<WordDictionary> createSystemWordList() {
		return List.of(new WordDictionary("직원", "STAFF", "STF")
					  ,new WordDictionary("번호", "NUMBER", "NO"));
	}
}
