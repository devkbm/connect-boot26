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
import com.like.system.core.web.util.WebControllerUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SurveyController {

	private SurveyService surveyService;
	
	private SurveyQueryService surveyQueryService;
	
	public SurveyController(SurveyService surveyService, SurveyQueryService surveyQueryService) {
		this.surveyService = surveyService;		
		this.surveyQueryService = surveyQueryService;
	}
	
	@GetMapping("/api/survey/form")
	public ResponseEntity<?> getSurveyFormList(SurveyFormDTO.SearchSurveyForm dto) {				
		
		List<SurveyForm> list = surveyQueryService.getSurveyFormList(dto); 		
								
		return WebControllerUtil.getResponse(list																					
											,String.format("%d 건 조회되었습니다.", list.size()));
	}
	
	@GetMapping("/api/survey/form/{id}")
	public ResponseEntity<?> getSurveyForm(@PathVariable(value="id") Long formId) {				
		
		SurveyForm surveryForm = surveyService.getSurveyForm(formId); 		
								
		return WebControllerUtil.getResponse(surveryForm																					
											,String.format("%d 건 조회되었습니다.", surveryForm != null ? 1 : 0));
	}
	
		
	@PostMapping("/api/survey/form")
	public ResponseEntity<?> saveSurveyForm(@Valid @RequestBody SurveyFormDTO.SaveSurveyForm dto) {							
																			
		surveyService.saveSurveyForm(dto);			
										 					
		return WebControllerUtil.getResponse(null										
											,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/api/survey/form/{id}")
	public ResponseEntity<?> deleteSurveyForm(@PathVariable(value="id") Long formId) {				
		
		surveyService.deleteSurveyForm(formId); 		
								
		return WebControllerUtil.getResponse(null																		
											,String.format("%d 건 삭제되었습니다.", 1));
	}
	
	@GetMapping("/api/survey/form/{formId}/item/{itemId}")
	public ResponseEntity<?> getSurveyItem(@PathVariable(value="formId") Long formId
										  ,@PathVariable(value="itemId") Long itemId) {				
		
		SurveyItem surveryForm = surveyService.getSurveyItem(formId, itemId); 		
								
		return WebControllerUtil.getResponse(surveryForm											
											,String.format("%d 건 조회되었습니다.", surveryForm != null ? 1 : 0));
	}
	
	@PostMapping("/api/survey/form/item") 
	public ResponseEntity<?> saveSurveyItem(@Valid @RequestBody SurveyFormDTO.SaveSurveyItem dto) {						 					
																			
		surveyService.saveSurveyItem(dto);			
										 					
		return WebControllerUtil.getResponse(null																			
											,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/api/survey/form/{formId}/item/{itemId}")
	public ResponseEntity<?> deleteSurveyForm(@PathVariable(value="formId") Long formId
			  								 ,@PathVariable(value="itemId") Long itemId) {				
		
		surveyService.deleteSurveyItem(formId, itemId); 		
								
		return WebControllerUtil.getResponse(null																				
											,String.format("%d 건 삭제되었습니다.", 1));
	}
}
