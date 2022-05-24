package com.like.cooperation.survey.surveyform.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.survey.surveyform.boundary.SurveyFormDTO;
import com.like.cooperation.survey.surveyform.domain.SurveyForm;
import com.like.cooperation.survey.surveyform.infra.jparepository.SurveyQueryRepositoryImpl;

@Service
@Transactional(readOnly = true)
public class SurveyQueryService {

	private SurveyQueryRepositoryImpl surveyRepository; 
	
	public SurveyQueryService(SurveyQueryRepositoryImpl surveyRepository) {
		this.surveyRepository = surveyRepository;
	}
	
	public List<SurveyForm> getSurveyFormList(SurveyFormDTO.SearchForm dto) {
		return surveyRepository.getSurveyFormList(dto);
	}
}
