package com.like.cooperation.survey.surveyform.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.survey.surveyform.boundary.SurveyFormDTO;
import com.like.cooperation.survey.surveyform.domain.SurveyForm;
import com.like.cooperation.survey.surveyform.domain.SurveyItem;
import com.like.cooperation.survey.surveyform.domain.SurveyRepository;

@Service
@Transactional
public class SurveyService {

	private SurveyRepository surveyRepository; 
	
	public SurveyService(SurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
	}
	
	public SurveyForm getSurveyForm(Long id) {
		return surveyRepository.findById(id).orElse(null);
	}
	
	public void saveSurveyForm(SurveyFormDTO.SaveSurveyForm dto) {
		SurveyForm entity = null;
		
		if (dto.getFormId() == null) {
			entity = dto.newSurveyForm();
		} else {
			entity = surveyRepository.findById(dto.getFormId()).orElse(null);
			
			dto.modifySurveyForm(entity);
		}
			
		surveyRepository.save(entity);
	}
	
	public void deleteSurveyForm(Long id) {
		SurveyForm entity = surveyRepository.findById(id).orElse(null);
		surveyRepository.delete(entity);
	}
	
	public SurveyItem getSurveyItem(Long formId, Long itemId) {
		return surveyRepository.findById(formId).orElse(null).getItem(itemId);
	}
		
	public void saveSurveyItem(SurveyFormDTO.SaveSurveyItem dto) {
		SurveyForm form = surveyRepository.findById(dto.getFormId()).orElse(null);
		SurveyItem item = null;
		
		if (dto.getItemId() == null) {
			item = dto.newSaveSurveyItem(form);
			form.addItem(item);
		} else {
			item = form.getItem(dto.getItemId());
			dto.modifySaveSurveyItem(item);
		}
		
		surveyRepository.save(form);
	}
	
	public void deleteSurveyItem(Long formId, Long itemId) {
		SurveyForm entity = surveyRepository.findById(formId).orElse(null);		
		entity.removeItem(itemId);				
	}
}
