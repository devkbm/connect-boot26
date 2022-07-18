package com.like.system.term.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.system.term.domain.QTermDictionary;
import com.like.system.term.domain.TermDictionary;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TermDTO {

	@Data
	public static class SearchTerm implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QTermDictionary qType = QTermDictionary.termDictionary;
						
		String domain;
		
		String term;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
									
			builder.and(likeMenuId(this.domain))
				   .and(likeTerm(this.term));
									
			return builder;
		}
		
		private BooleanExpression likeMenuId(String domain) {
			return null;
			//return hasText(domain) ? qType.domain.like("%"+this.domain+"%") : null;					
		}
		
		private BooleanExpression likeTerm(String term) {
			return hasText(term) ? qType.term.like("%"+this.term+"%") : null;					
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveTerm implements Serializable {
				
		private static final long serialVersionUID = 6104580538309815203L;

		Long pkTerm;	
				
		@NotEmpty(message = "도메인은 필수 입력 값입니다.")
		String domain;
				
		@NotEmpty(message = "용어는 필수 입력 값입니다.")
		String term;
					
		String nameKor;
				
		String abbreviationKor;
				
		String nameEng;
					
		String abbreviationEng;
				
		String description;
				
		String comment;
		
		public TermDictionary newEntity() {
			return null; /* TermDictionary.builder()
								 .domain(domain)
								 .term(term)								 
								 .nameEng(nameEng)
								 .abbreviationEng(abbreviationEng)
								 .description(description)
								 .comment(comment)
								 .build();
								 */
		}
		
		public void modifyEntity(TermDictionary entity) {
			entity.modifyEntity(nameEng
					           ,abbreviationEng
					           ,description
					           ,comment);
			
		}
		
		public static SaveTerm convert(TermDictionary entity) {
			return null; /*
					SaveTerm.builder()						   
						   .domain(entity.getDomain())
						   .term(entity.getTerm())						   
						   .nameEng(entity.getNameEng())
						   .abbreviationEng(entity.getAbbreviationEng())
						   .description(entity.getDescription())
						   .comment(entity.getComment())
						   .build();
						   */
		}
	}
}
