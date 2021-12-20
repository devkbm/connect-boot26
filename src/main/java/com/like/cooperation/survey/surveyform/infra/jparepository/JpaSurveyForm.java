package com.like.cooperation.survey.surveyform.infra.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.cooperation.survey.surveyform.domain.SurveyForm;

@Repository
public interface JpaSurveyForm extends JpaRepository<SurveyForm, Long> {

}
