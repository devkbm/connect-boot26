package com.like.system.term.web;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.ResponseEntityUtil;
import com.like.system.term.boundary.TermDTO;
import com.like.system.term.domain.TermDictionary;
import com.like.system.term.service.TermService;

@RestController
public class TermController {
	
	private TermService termService;

	public TermController(TermService termService) {
		this.termService = termService;
	}
		
	@GetMapping("/api/common/terms/{id}")
	public ResponseEntity<?> getTerm(@PathVariable Long id) {
		
		TermDictionary term = termService.getTerm(id);								
		
		return ResponseEntityUtil.toOne(term											
									   ,String.format("%d 건 조회되었습니다.", term == null ? 0 : 1));
	}			
		
	@PostMapping("/api/common/terms")
	public ResponseEntity<?> saveTerm(@Valid @RequestBody TermDTO.SaveTerm dto) {
														
		termService.saveTerm(dto);										
		
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 저장되었습니다.", 1));
	
	}
					
	@DeleteMapping("/api/common/terms/{id}")
	public ResponseEntity<?> delTerm(@PathVariable Long id) {
								
		termService.deleteTerm(id);										
		
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 삭제되었습니다.", 1));
	}		
	
}