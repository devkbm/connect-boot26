package com.like.hrm.hrmtypecode.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmtypecode.boundary.SaveHrmRelationCode;
import com.like.hrm.hrmtypecode.domain.HrmRelationCode;
import com.like.hrm.hrmtypecode.service.HrmRelationCodeService;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class HrmRelationCodeController {

	private HrmRelationCodeService service;
	
	public HrmRelationCodeController(HrmRelationCodeService service) {
		this.service = service;
	}
	
	@GetMapping("/hrm/hrmrelation/{id}")
	public ResponseEntity<?> getHrmRelationCode(@PathVariable Long id) {				
		
		HrmRelationCode hrmType = service.getRelationCode(id);
					
		return ResponseEntityUtil.toOne(hrmType											
									   ,String.format("%d 건 조회되었습니다.", hrmType == null ? 0 : 1));
	}
	
	@RequestMapping(value={"/hrm/hrmrelation"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveHrmRelationCode(@RequestBody SaveHrmRelationCode dto) {				
																		
		service.saveRelationCode(dto);						
								 					
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/hrm/hrmrelation/{id}")
	public ResponseEntity<?> deleteHrmRelationCode(@PathVariable Long id) {				
						
		service.deleteRelationCode(id);						
								 					
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 삭제되었습니다.", 1));
	}
}
