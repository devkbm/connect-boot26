package com.like.system.term.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.dto.HtmlSelectOptionRecord;
import com.like.system.core.dto.HtmlSelectOptionable;
import com.like.system.core.message.MessageUtil;
import com.like.system.term.boundary.TermDTO;
import com.like.system.term.domain.SystemType;
import com.like.system.term.service.TermQueryService;

@RestController
public class TermQueryController {

	private TermQueryService service;
	
	public TermQueryController(TermQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/system/terms")
	public ResponseEntity<?> getTermList(TermDTO.Search contidion) {
				
		List<TermDTO.FormTerm> list = service.getTermList(contidion)
											 .stream()
											 .map(e -> TermDTO.FormTerm.convert(e))
											 .toList(); 							
							
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/system/terms/systemType")
	public ResponseEntity<?> getSystemTypeList() {				
		
		List<HtmlSelectOptionRecord> list = HtmlSelectOptionable.fromEnum(SystemType.class);  // HtmlSelectOptionRecord.fromEnum(SystemType.class);			
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
