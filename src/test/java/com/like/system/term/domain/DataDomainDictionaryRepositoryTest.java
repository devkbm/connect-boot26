package com.like.system.term.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.like.system.term.domain.DataDomainDictionary.Database;

import lombok.Getter;

@DisplayName("DataDomainDictionaryRepository 클래스")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class DataDomainDictionaryRepositoryTest {

	//@Autowired
	//private DataDomainDictionaryRepository repository;

	@ExtendWith(SpringExtension.class)
	@DataJpaTest
	@Getter
	class JpaTest {
	  @Autowired DataDomainDictionaryRepository repository;
	}
	
	@Nested
	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	class of_메소드는 {
					
		@Nested
		@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
		class 단일_단어로_생성될_경우 extends JpaTest {					
			
			@Test
			@DisplayName("리턴한다")	
			void 리턴한다() {			
				DataDomainDictionary entity = DataDomainDictionary.builder()
																  .database(Database.MYSQL)
																  .domainName("직원번호")
																  .dataType("VARCHAR")
																  .columnSize("8")
																  .build();			

				//When
				
				getRepository().save(entity);		
				DataDomainDictionary result = getRepository().findById("MYSQL_직원번호").orElse(null);					
				
				//Then
				assertThat(result.getId()).isEqualTo("MYSQL_직원번호");
				assertThat(result.getDatabase()).isEqualTo(Database.MYSQL);
				assertThat(result.getDomainName()).isEqualTo("직원번호");		
				assertThat(result.getDataType()).isEqualTo("VARCHAR");
				assertThat(result.getColumnSize()).isEqualTo("8");
			}
			
		}
	}
	
	/*
	@DisplayName("단어 리스트로 용어 생성")
	@Test
	void saveDataDomain() {
		//Given
		DataDomainDictionary entity = DataDomainDictionary.builder()
														  .database(Database.MYSQL)
														  .domainName("직원번호")
														  .dataType("VARCHAR")
														  .columnSize("8")
														  .build();			
		
		//When
		repository.save(entity);		
		DataDomainDictionary result = repository.findById("MYSQL_직원번호").orElse(null);					
		
		//Then
		assertThat(result.getId()).isEqualTo("MYSQL_직원번호");
		assertThat(result.getDatabase()).isEqualTo(Database.MYSQL);
		assertThat(result.getDomainName()).isEqualTo("직원번호");		
		assertThat(result.getDataType()).isEqualTo("VARCHAR");
		assertThat(result.getColumnSize()).isEqualTo("8");
	}
	*/
}
