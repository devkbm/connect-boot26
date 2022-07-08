package com.like.hrm.duty.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.duty.service.DutyCodeCommandService;
import com.like.hrm.dutycode.boundary.DutyCodeDTO;
import com.like.hrm.dutycode.domain.DutyCode;
import com.like.system.core.message.MessageUtil;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class DutyCodeController {

	private DutyCodeCommandService service;		
	
	public DutyCodeController(DutyCodeCommandService service) {
		this.service = service;		
	}		
	
	@GetMapping("/api/hrm/dutycode/{id}")
	public ResponseEntity<?> getDutyCode(@PathVariable(value="id") String id) {
		
		DutyCode entity = service.getDutyCode(id);
					
		return ResponseEntityUtil.toOne(entity		
									   ,MessageUtil.getQueryMessage(entity == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/dutycode")
	public ResponseEntity<?> saveDutyCode(@RequestBody DutyCodeDTO.SaveDutyCode dto) {				
									
		service.saveDutyCode(dto);						
								 					
		return ResponseEntityUtil.toList(null
										,MessageUtil.getSaveMessage(1));
	}
	
		
	@DeleteMapping("/api/hrm/dutycode/{id}")
	public ResponseEntity<?> deleteDutyCode(@PathVariable(value="id") String id) {				
																		
		service.deleteDutyCode(id);						
								 					
		return ResponseEntityUtil.toList(null	
										,MessageUtil.getDeleteMessage(1));
	}
}
