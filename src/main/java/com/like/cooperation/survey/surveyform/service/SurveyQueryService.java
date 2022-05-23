package com.like.cooperation.survey.surveyform.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.survey.surveyform.boundary.SurveyFormDTO;
import com.like.cooperation.survey.surveyform.domain.SurveyForm;
import com.like.cooperation.survey.surveyform.infra.jparepository.SurveyJpaRepository;

@Service
@Transactional(readOnly = true)
public class SurveyQueryService {

	private SurveyJpaRepository surveyRepository; 
	
	public SurveyQueryService(SurveyJpaRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
	}
	
	public List<SurveyForm> getSurveyFormList(SurveyFormDTO.SearchSurveyForm dto) {
		return surveyRepository.getSurveyFormList(dto);
	}
}
