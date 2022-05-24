package com.like.cooperation.survey.surveyform.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.survey.surveyform.boundary.SurveyFormDTO.SearchForm;
import com.like.cooperation.survey.surveyform.domain.QSurveyForm;
import com.like.cooperation.survey.surveyform.domain.SurveyForm;
import com.like.cooperation.survey.surveyform.domain.SurveyQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class SurveyQueryRepositoryImpl implements SurveyQueryRepository {
	
	private JPAQueryFactory queryFactory;
			
	public SurveyQueryRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
		
	@Override
	public List<SurveyForm> getSurveyFormList(SearchForm dto) {

		return queryFactory.selectFrom(QSurveyForm.surveyForm)
						   .where(dto.getBooleanBuilder())
						   .fetch();
	}

}
