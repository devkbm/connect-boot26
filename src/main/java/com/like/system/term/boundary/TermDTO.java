package com.like.system.term.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.like.system.term.domain.DataDomainDictionary;
import com.like.system.term.domain.QTermDictionary;
import com.like.system.term.domain.TermDictionary;
import com.like.system.term.domain.WordDictionary;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class TermDTO {

	public record Search(			
			String term,
			String dataDomainName
			) {
				
		private static final QTermDictionary qType = QTermDictionary.termDictionary;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
									
			builder.and(likeTerm(this.term))
				   .and(likeDataDomain(this.dataDomainName));
									
			return builder;
		}
		
		private BooleanExpression likeTerm(String term) {
			return hasText(term) ? qType.term.like("%"+this.term+"%") : null;					
		}
		
		private BooleanExpression likeDataDomain(String dataDomain) {			
			return hasText(dataDomain) ? qType.dataDomain.domainName.like("%"+this.dataDomainName+"%") : null;					
		}		
		
	}
	
	@Builder
	public static record FormTerm(
			String organizationCode,
			String clientAppUrl,
			String termId,
			String system,
			@NotEmpty(message = "용어는 필수 입력 값입니다.")
			List<String> term,
			String termEng,
			String columnName,
			String dataDomainId,
			String dataDomainName,
			String description,
			String comment
			) {
		
		private static List<String> toList(String term) {
			String[] terms = term.split("_");
			List<String> list = new ArrayList<String>(terms.length);
			
			for (int i=0; i<terms.length; i++) {				
				list.add(terms[i]);
			}
			
			return list;
		}		
		
		public TermDictionary newEntity(WordDictionary word, DataDomainDictionary dataDomain) {												
			return TermDictionary.of(system, word, termEng, dataDomain, description, comment);
		}
		
		public TermDictionary newEntity(List<WordDictionary> word, DataDomainDictionary dataDomain) {
			return TermDictionary.of(system, word, termEng, dataDomain, description, comment);
		}
		
		
		public void modifyEntity(TermDictionary entity, DataDomainDictionary dataDomain) {
			
			entity.modifyEntity(termEng					           
					           ,dataDomain
					           ,description
					           ,comment);			
			
		}
		
		public static FormTerm convert(TermDictionary entity) {
			return FormTerm.builder()						   
						   .termId(entity.getId())
						   .system(entity.getSystem())						   
						   .term(toList(entity.getTerm()))
						   .termEng(entity.getTermEng())
						   .columnName(entity.getColumnName())
						   .dataDomainId(entity.getDataDomain().getId())
						   .dataDomainName(entity.getDataDomain().getDomainName())
						   .description(entity.getDescription())
						   .comment(entity.getComment())
						   .build();						   
		}
			
	}
		
	
	
}
