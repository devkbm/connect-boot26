package com.like.cooperation.survey.surveyform.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SurveyRepository extends JpaRepository<SurveyForm, Long> , QuerydslPredicateExecutor<SurveyForm> {
	
}
