package com.like.cooperation.survey.surveyform.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.survey.surveyform.boundary.SurveyFormDTO;
import com.like.cooperation.survey.surveyform.domain.SurveyForm;
import com.like.cooperation.survey.surveyform.domain.SurveyItem;
import com.like.cooperation.survey.surveyform.service.SurveyQueryService;
import com.like.cooperation.survey.surveyform.service.SurveyService;
import com.like.system.core.message.MessageUtil;

@RestController
public class SurveyController {

	private SurveyService surveyService;
	
	private SurveyQueryService surveyQueryService;
	
	public SurveyController(SurveyService surveyService, SurveyQueryService surveyQueryService) {
		this.surveyService = surveyService;		
		this.surveyQueryService = surveyQueryService;
	}
	
	@GetMapping("/api/survey/form")
	public ResponseEntity<?> getSurveyFormList(SurveyFormDTO.SearchForm dto) {				
		
		List<SurveyForm> list = surveyQueryService.getSurveyFormList(dto); 		
								
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/survey/form/{formId}")
	public ResponseEntity<?> getSurveyForm(@PathVariable Long formId) {				
		
		SurveyForm surveryForm = surveyService.getSurveyForm(formId); 		
								
		return toOne(surveryForm, MessageUtil.getQueryMessage(surveryForm != null ? 1 : 0));
	}
	
		
	@PostMapping("/api/survey/form")
	public ResponseEntity<?> saveSurveyForm(@Valid @RequestBody SurveyFormDTO.SaveForm dto) {							
																			
		surveyService.saveSurveyForm(dto);			
										 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/survey/form/{id}")
	public ResponseEntity<?> deleteSurveyForm(@PathVariable Long formId) {				
		
		surveyService.deleteSurveyForm(formId); 		
								
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
	@GetMapping("/api/survey/form/{formId}/item/{itemId}")
	public ResponseEntity<?> getSurveyItem(@PathVariable Long formId
										  ,@PathVariable Long itemId) {				
		
		SurveyItem surveryForm = surveyService.getSurveyItem(formId, itemId); 		
								
		return toOne(surveryForm, MessageUtil.getQueryMessage(surveryForm != null ? 1 : 0));
	}
	
	@PostMapping("/api/survey/form/item") 
	public ResponseEntity<?> saveSurveyItem(@Valid @RequestBody SurveyFormDTO.SaveItem dto) {						 					
																			
		surveyService.saveSurveyItem(dto);			
										 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/survey/form/{formId}/item/{itemId}")
	public ResponseEntity<?> deleteSurveyForm(@PathVariable Long formId
			  								 ,@PathVariable Long itemId) {
		
		surveyService.deleteSurveyItem(formId, itemId); 		
								
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
