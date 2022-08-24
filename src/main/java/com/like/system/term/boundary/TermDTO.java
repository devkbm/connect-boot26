package com.like.system.term.boundary;

import static org.springframework.util.StringUtils.hasText;

import javax.validation.constraints.NotEmpty;

import com.like.system.term.domain.QTermDictionary;
import com.like.system.term.domain.TermDictionary;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

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
	
	public record FormTerm(
			String clientAppUrl,
			Long pkTerm,
			@NotEmpty(message = "도메인은 필수 입력 값입니다.")
			String domain,
			@NotEmpty(message = "용어는 필수 입력 값입니다.")
			String term,
			String nameKor,
			String abbreviationKor,
			String nameEng,
			String abbreviationEng,
			String description,
			String comment
			) {
		
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
		
		public static FormTerm convert(TermDictionary entity) {
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
