package com.like.hrm.duty.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.duty.service.DutyCodeCommandService;
import com.like.hrm.dutycode.boundary.DutyCodeDTO;
import com.like.hrm.dutycode.domain.DutyCode;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class DutyCodeController {

	private DutyCodeCommandService service;		
	
	public DutyCodeController(DutyCodeCommandService service) {
		this.service = service;		
	}		
	
	@GetMapping("/hrm/dutycode/{id}")
	public ResponseEntity<?> getDutyCode(@PathVariable(value="id") String id) {
		
		DutyCode entity = service.getDutyCode(id);
					
		return ResponseEntityUtil.toOne(entity											
									   ,String.format("%d 건 조회되었습니다.", entity == null ? 0 : 1));
	}
	
	@RequestMapping(value={"/hrm/dutycode"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveDutyCode(@RequestBody DutyCodeDTO.SaveDutyCode dto) {				
									
		service.saveDutyCode(dto);						
								 					
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 저장되었습니다.", 1));
	}
	
		
	@DeleteMapping("/hrm/dutycode/{id}")
	public ResponseEntity<?> deleteDutyCode(@PathVariable(value="id") String id) {				
																		
		service.deleteDutyCode(id);						
								 					
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 삭제되었습니다.", 1));
	}
}
