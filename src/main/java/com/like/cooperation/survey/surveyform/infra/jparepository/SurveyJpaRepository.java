package com.like.cooperation.survey.surveyform.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.survey.surveyform.boundary.SurveyFormDTO.SearchSurveyForm;
import com.like.cooperation.survey.surveyform.domain.QSurveyForm;
import com.like.cooperation.survey.surveyform.domain.SurveyForm;
import com.like.cooperation.survey.surveyform.domain.SurveyQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class SurveyJpaRepository implements SurveyQueryRepository {
	
	private JPAQueryFactory queryFactory;
			
	public SurveyJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
		
	@Override
	public List<SurveyForm> getSurveyFormList(SearchSurveyForm dto) {

		return queryFactory.selectFrom(QSurveyForm.surveyForm)
						   .where(dto.getBooleanBuilder())
						   .fetch();
	}

}
