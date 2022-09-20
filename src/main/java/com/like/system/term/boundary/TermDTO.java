package com.like.system.term.boundary;

import static org.springframework.util.StringUtils.hasText;

import javax.validation.constraints.NotEmpty;

import com.like.system.term.domain.QTermDictionary;
import com.like.system.term.domain.TermDictionary;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class TermDTO {

	public record Search(
			String domain,
			String term
			) {
				
		private static final QTermDictionary qType = QTermDictionary.termDictionary;
		
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
	
	@Builder
	public static record FormTerm(
			String organizationCode,
			String clientAppUrl,
			String termId,
			String system,
			@NotEmpty(message = "용어는 필수 입력 값입니다.")
			String term,
			String termEng,
			String columnName,
			String description,
			String comment
			) {
		
		public TermDictionary newEntity() {
			return TermDictionary.builder()
								 .system(system)
								 .term(term)
								 .termEng(termEng)
								 .columnName(columnName)
								 //.dataDomain(dataDomain)
								 .description(description)
								 .comment(comment)
								 .build();					
		}
		
		public void modifyEntity(TermDictionary entity) {
			/*
			entity.modifyEntity(nameEng
					           ,abbreviationEng
					           ,description
					           ,comment);
					           */
			
		}
		
		public static FormTerm convert(TermDictionary entity) {
			return FormTerm.builder()						   
						   .termId(entity.getId())
						   .system(entity.getSystem())						   
						   .term(entity.getTerm())
						   .termEng(entity.getTermEng())
						   .columnName(entity.getColumnName())
						   .description(entity.getDescription())
						   .comment(entity.getComment())
						   .build();						   
		}
	}
		
	
	
}
