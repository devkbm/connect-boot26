package com.like.cooperation.survey.surveyform.domain;

import java.util.List;

import com.like.cooperation.survey.surveyform.boundary.SurveyFormDTO;

public interface SurveyRepository {

	SurveyForm getSurveyForm(Long id);
	
	void saveSureyForm(SurveyForm surveyForm);
	
	void deleteSurveyForm(SurveyForm surveyForm);
		
	List<SurveyForm> getSurveyFormList(SurveyFormDTO.SearchSurveyForm dto);
}
