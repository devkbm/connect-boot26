package com.like.system.term.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.term.boundary.TermDTO;
import com.like.system.term.domain.TermDictionary;
import com.like.system.term.service.TermQueryService;

@RestController
public class TermQueryController {

	private TermQueryService service;
	
	public TermQueryController(TermQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/common/terms")
	public ResponseEntity<?> getTermList(TermDTO.SearchTerm contidion) {
				
		List<TermDictionary> list = service.getTermList(contidion); 							
							
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
