package com.like.hrm.hrmtypecode.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmtypecode.boundary.SaveHrmRelationCode;
import com.like.hrm.hrmtypecode.domain.HrmRelationCode;
import com.like.hrm.hrmtypecode.service.HrmRelationCodeService;
import com.like.system.core.message.MessageUtil;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class HrmRelationCodeController {

	private HrmRelationCodeService service;
	
	public HrmRelationCodeController(HrmRelationCodeService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/hrmrelation/{id}")
	public ResponseEntity<?> getHrmRelationCode(@PathVariable Long id) {				
		
		HrmRelationCode hrmRelationCode = service.getRelationCode(id);
					
		return ResponseEntityUtil.toOne(hrmRelationCode			
									   ,MessageUtil.getQueryMessage(hrmRelationCode == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/hrmrelation")
	public ResponseEntity<?> saveHrmRelationCode(@RequestBody SaveHrmRelationCode dto) {				
																		
		service.saveRelationCode(dto);						
								 					
		return ResponseEntityUtil.toList(null	
										,MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/hrm/hrmrelation/{id}")
	public ResponseEntity<?> deleteHrmRelationCode(@PathVariable Long id) {				
						
		service.deleteRelationCode(id);						
								 					
		return ResponseEntityUtil.toList(null	
										,MessageUtil.getDeleteMessage(1));
	}
}
