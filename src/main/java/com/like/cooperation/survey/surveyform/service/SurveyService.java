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
	
	public void saveSurveyForm(SurveyFormDTO.SaveForm dto) {
		SurveyForm entity = dto.formId() == null ? null : surveyRepository.findById(dto.formId()).orElse(null);
		
		if (dto.formId() == null) {
			entity = dto.newSurveyForm();
		} else {						
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
		
	public void saveSurveyItem(SurveyFormDTO.SaveItem dto) {
		SurveyForm form = surveyRepository.findById(dto.formId()).orElse(null);
		SurveyItem item = null;
		
		if (dto.itemId() == null) {
			item = dto.newEntity(form);
			form.addItem(item);
		} else {
			item = form.getItem(dto.itemId());
			dto.modifyEntity(item);
		}
		
		surveyRepository.save(form);
	}
	
	public void deleteSurveyItem(Long formId, Long itemId) {
		SurveyForm entity = surveyRepository.findById(formId).orElse(null);		
		entity.removeItem(itemId);				
	}
}
