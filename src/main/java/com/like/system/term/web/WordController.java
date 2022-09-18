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
import com.like.system.term.boundary.WordDTO;
import com.like.system.term.service.WordService;

@RestController
public class WordController {

	private WordService service;
	
	public WordController(WordService service) {
		this.service = service;
	}
	
	@GetMapping("/api/system/word/{id}")
	public ResponseEntity<?> getWord(@PathVariable String id) {
		
		WordDTO.FormWord dto = WordDTO.FormWord.convert(service.get(id));								
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}			
		
	@PostMapping("/api/system/word")
	public ResponseEntity<?> saveWord(@Valid @RequestBody WordDTO.FormWord dto) {
														
		service.save(dto);										
		
		return toList(null, MessageUtil.getSaveMessage(1));
	
	}
					
	@DeleteMapping("/api/system/word/{id}")
	public ResponseEntity<?> delWord(@PathVariable String id) {
								
		service.delete(id);										
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}	
	
}
