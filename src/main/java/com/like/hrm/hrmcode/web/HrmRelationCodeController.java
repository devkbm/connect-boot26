package com.like.hrm.hrmcode.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.boundary.SaveHrmRelationCode;
import com.like.hrm.hrmcode.domain.HrmRelationCode;
import com.like.hrm.hrmcode.service.HrmRelationCodeService;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmRelationCodeController {

	private HrmRelationCodeService service;
	
	public HrmRelationCodeController(HrmRelationCodeService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/hrmrelation/{id}")
	public ResponseEntity<?> getHrmRelationCode(@PathVariable Long id) {				
		
		HrmRelationCode hrmRelationCode = service.getRelationCode(id);
					
		return toOne(hrmRelationCode, MessageUtil.getQueryMessage(hrmRelationCode == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/hrmrelation")
	public ResponseEntity<?> saveHrmRelationCode(@RequestBody SaveHrmRelationCode dto) {				
																		
		service.saveRelationCode(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/hrm/hrmrelation/{id}")
	public ResponseEntity<?> deleteHrmRelationCode(@PathVariable Long id) {				
						
		service.deleteRelationCode(id);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
