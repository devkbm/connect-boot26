package com.like.cooperation.survey.surveyform.domain;

import java.util.List;

import com.like.cooperation.survey.surveyform.boundary.SurveyFormDTO;

public interface SurveyQueryRepository {

	List<SurveyForm> getSurveyFormList(SurveyFormDTO.SearchForm dto);
}
