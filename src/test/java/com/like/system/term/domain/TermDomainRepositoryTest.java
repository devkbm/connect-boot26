package com.like.system.term.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.like.system.term.domain.TermDomain.Database;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class TermDomainRepositoryTest {

	@Autowired
	private TermDomainRepository repository;

	
	@DisplayName("저장시 ID가 NULL이면 오류")
	@Test
	void saveSystemWord_ERROR() {			
			
		assertThatExceptionOfType(NullPointerException .class).isThrownBy(() -> { 
			new SystemWord(null, "TEST2");
		});	
	}
	
	
	@DisplayName("시스템단어 저장")
	@Test
	void saveSystemWord() {
		//Given
		// Database database, String domainName, String dataType, String columnSize
		TermDomain entity = TermDomain.builder()
									  .database(Database.MYSQL)
									  .domainName("직원번호")
									  .dataType("VARCHAR")
									  .columnSize("8")
									  .build();			
		
		//When
		repository.save(entity);		
		TermDomain result = repository.findById("MYSQL_직원번호").orElse(null);					
		
		//Then
		assertThat(result.getId()).isEqualTo("MYSQL_직원번호");
		assertThat(result.getDatabase()).isEqualTo(Database.MYSQL);
		assertThat(result.getDomainName()).isEqualTo("직원번호");		
		assertThat(result.getDataType()).isEqualTo("VARCHAR");
		assertThat(result.getColumnSize()).isEqualTo("8");
	}
}
