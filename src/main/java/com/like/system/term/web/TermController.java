package com.like.system.term.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
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
		
		return toOne(term, MessageUtil.getQueryMessage(term == null ? 0 : 1));
	}			
		
	@PostMapping("/api/common/terms")
	public ResponseEntity<?> saveTerm(@Valid @RequestBody TermDTO.SaveTerm dto) {
														
		termService.saveTerm(dto);										
		
		return toList(null, MessageUtil.getSaveMessage(1));
	
	}
					
	@DeleteMapping("/api/common/terms/{id}")
	public ResponseEntity<?> delTerm(@PathVariable Long id) {
								
		termService.deleteTerm(id);										
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}		
	
}