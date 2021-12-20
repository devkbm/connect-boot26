package com.like.system.term.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.exception.ControllerException;
import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.term.boundary.TermDTO;
import com.like.system.term.domain.TermDictionary;
import com.like.system.term.service.TermService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TermController {
	
	private TermService termService;

	public TermController(TermService termService) {
		this.termService = termService;
	}
		
	@GetMapping("/api/common/terms/{id}")
	public ResponseEntity<?> getTerm(@PathVariable(value="id") Long id) {
		
		TermDictionary term = termService.getTerm(id);								
		
		return WebControllerUtil
				.getResponse(term											
							,String.format("%d 건 조회되었습니다.", term == null ? 0 : 1)
							,HttpStatus.OK);
	}	
		
	@GetMapping("/api/common/terms")
	public ResponseEntity<?> getTermList(TermDTO.SearchTerm contidion) {
				
		List<TermDictionary> list = termService.getTermList(contidion); 							
							
		return WebControllerUtil
				.getResponse(list											
							,String.format("%d 건 조회되었습니다.", list.size())
							,HttpStatus.OK);
	}	
		
	@PostMapping("/api/common/terms")
	public ResponseEntity<?> saveTerm(@RequestBody @Valid TermDTO.SaveTerm dto, BindingResult result) {
					
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		}			
		
		log.info(dto.toString());
		
		termService.saveTerm(dto);										
		
		return WebControllerUtil
				.getResponse(null											
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	
	}
					
	@DeleteMapping("/api/common/terms/{id}")
	public ResponseEntity<?> delTerm(@PathVariable(value="id") Long id) {
								
		termService.deleteTerm(id);										
		
		return WebControllerUtil
				.getResponse(null											
							,String.format("%d 건 삭제되었습니다.", 1)
							,HttpStatus.OK);
	}		
	
}