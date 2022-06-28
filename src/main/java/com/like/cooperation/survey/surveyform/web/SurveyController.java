package com.like.cooperation.survey.surveyform.web;

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
import com.like.system.core.web.util.WebResponseUtil;

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
								
		return WebResponseUtil.toList(list																					
											,String.format("%d 건 조회되었습니다.", list.size()));
	}
	
	@GetMapping("/api/survey/form/{formId}")
	public ResponseEntity<?> getSurveyForm(@PathVariable Long formId) {				
		
		SurveyForm surveryForm = surveyService.getSurveyForm(formId); 		
								
		return WebResponseUtil.toOne(surveryForm																					
											,String.format("%d 건 조회되었습니다.", surveryForm != null ? 1 : 0));
	}
	
		
	@PostMapping("/api/survey/form")
	public ResponseEntity<?> saveSurveyForm(@Valid @RequestBody SurveyFormDTO.SaveForm dto) {							
																			
		surveyService.saveSurveyForm(dto);			
										 					
		return WebResponseUtil.toList(null										
											,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/api/survey/form/{id}")
	public ResponseEntity<?> deleteSurveyForm(@PathVariable Long formId) {				
		
		surveyService.deleteSurveyForm(formId); 		
								
		return WebResponseUtil.toList(null																		
											,String.format("%d 건 삭제되었습니다.", 1));
	}
	
	@GetMapping("/api/survey/form/{formId}/item/{itemId}")
	public ResponseEntity<?> getSurveyItem(@PathVariable Long formId
										  ,@PathVariable Long itemId) {				
		
		SurveyItem surveryForm = surveyService.getSurveyItem(formId, itemId); 		
								
		return WebResponseUtil.toOne(surveryForm											
											,String.format("%d 건 조회되었습니다.", surveryForm != null ? 1 : 0));
	}
	
	@PostMapping("/api/survey/form/item") 
	public ResponseEntity<?> saveSurveyItem(@Valid @RequestBody SurveyFormDTO.SaveItem dto) {						 					
																			
		surveyService.saveSurveyItem(dto);			
										 					
		return WebResponseUtil.toList(null																			
											,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/api/survey/form/{formId}/item/{itemId}")
	public ResponseEntity<?> deleteSurveyForm(@PathVariable Long formId
			  								 ,@PathVariable Long itemId) {
		
		surveyService.deleteSurveyItem(formId, itemId); 		
								
		return WebResponseUtil.toList(null																				
											,String.format("%d 건 삭제되었습니다.", 1));
	}
}
