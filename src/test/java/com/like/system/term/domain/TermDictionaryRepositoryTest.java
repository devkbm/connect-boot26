package com.like.system.term.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.like.system.core.jpa.JpaRepositoryAuditConfigTest;
import com.like.system.core.test.WithMockCustomUser;
import com.like.system.term.domain.DataDomainDictionary.Database;

import lombok.Getter;

@DisplayName("TermDictionaryRepository Test")
@Import(JpaRepositoryAuditConfigTest.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class TermDictionaryRepositoryTest { 
	
	@ExtendWith(SpringExtension.class)
	@WithMockCustomUser
	@DataJpaTest
	@Getter
	class RepositoryTest {
		@Autowired 
		TermDictionaryRepository repository;
	}
		
	@Nested
	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	class of_메소드는 extends RepositoryTest {
		
		DataDomainDictionary dataDomain = DataDomainDictionary.builder()
															  .database(Database.MYSQL)
															  .domainName("직원번호")
															  .dataType("VARCHAR")
															  .columnSize("8")
															  .build();			
		
		@Nested
		@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
		class 복합_단어로_생성될_경우 {
			
			List<WordDictionary> wordList = createWordDictionaryList();
			
			@Test
			@DisplayName("단어를 _(언더바)로 합쳐서 용어를 생성한다.")	
			void 단어를_언더바로_합쳐서_생성한다() {
				TermDictionary entity = TermDictionary.of("HRM", wordList, dataDomain, "용어설명", "비고");
				System.out.println(entity.toString());
				
				getRepository().save(entity);
				
				assertThat(entity.getId()).isEqualTo("HRM_직원_번호").as("용어사전ID는 시스템_복합단어(단어_단어)로 구성된다.");
				assertThat(entity.getTerm()).isEqualTo("직원_번호");
				assertThat(entity.getTermEng()).isEqualTo("STAFF_NUMBER");
				assertThat(entity.getPhysicalName()).isEqualTo("STF_NO");
			}
			
			List<WordDictionary> createWordDictionaryList() {
				return List.of(new WordDictionary("직원", "STAFF", "STF")
							  ,new WordDictionary("번호", "NUMBER", "NO"));
			}
		}
		
		@Nested
		@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
		class 단일_단어로_생성될_경우 {
			@Test
			@DisplayName("단어와 동일하게 용어를 생성한다.")	
			void 단어로_생성한다() {
				WordDictionary word = new WordDictionary("직원", "STAFF", "STF");
				TermDictionary entity = TermDictionary.of("HRM", word, dataDomain, "용어설명", "비고");
				
				getRepository().save(entity);
				
				assertThat(entity.getId()).isEqualTo("HRM_직원");
				assertThat(entity.getTerm()).isEqualTo("직원");
				assertThat(entity.getTermEng()).isEqualTo("STAFF");
				assertThat(entity.getPhysicalName()).isEqualTo("STF");
			}
		}
	}
		
}
