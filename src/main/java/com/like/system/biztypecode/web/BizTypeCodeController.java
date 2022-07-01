package com.like.system.biztypecode.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.biztypecode.boundary.BizDetailCodeDTO;
import com.like.system.biztypecode.boundary.BizTypeCodeDTO;
import com.like.system.biztypecode.domain.BizDetailCodeId;
import com.like.system.biztypecode.service.BizTypeCodeService;
import com.like.system.core.message.MessageUtil;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class BizTypeCodeController {

	private BizTypeCodeService service;
	
	public BizTypeCodeController(BizTypeCodeService service) {
		this.service = service;
	}
	
	@GetMapping("/common/biztype/{id}")
	public ResponseEntity<?> getBizTypeCode(@PathVariable String id) {
		
		BizTypeCodeDTO.FormBizTypeCode dto = BizTypeCodeDTO.FormBizTypeCode.convert(service.getBizTypeCode(id));
					
		return ResponseEntityUtil.toOne(dto		
									   ,MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
			
	@PostMapping("/common/biztype")	
	public ResponseEntity<?> saveHrmType(@RequestBody BizTypeCodeDTO.FormBizTypeCode dto) {				
																			
		service.saveBizTypeCode(dto);						
								 					
		return ResponseEntityUtil.toList(null											
										,MessageUtil.getSaveMessage(1));
	}
	
		
	@DeleteMapping("/common/biztype/{id}")
	public ResponseEntity<?> deleteHrmType(@PathVariable String id) {				
																		
		service.deleteBizTypeCode(id);						
								 					
		return ResponseEntityUtil.toList(null											
										,MessageUtil.getDeleteMessage(1));
	}
	
	@GetMapping("/common/biztype/{typeCode}/bizdetail/{detailCode}")
	public ResponseEntity<?> getBizDetailCode(@PathVariable String typeCode
											 ,@PathVariable String detailCode) {
		
		BizDetailCodeDTO.FormBizDetailCode dto = BizDetailCodeDTO.FormBizDetailCode.convert(service.getBizDetailCode(new BizDetailCodeId(typeCode, detailCode)));
					
		return ResponseEntityUtil.toOne(dto											
									   ,MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
			
	@PostMapping("/common/biztype/bizdetail")	
	public ResponseEntity<?> saveBizDetailCode(@RequestBody BizDetailCodeDTO.FormBizDetailCode dto) {				
																		
		service.saveBizDetailCode(dto);						
								 					
		return ResponseEntityUtil.toList(null											
										,MessageUtil.getSaveMessage(1));
	}	
		
	@DeleteMapping("/common/biztype/{typeCode}/bizdetail/{detailCode}")
	public ResponseEntity<?> deleteBizDetailCode(@PathVariable String typeCode
			 									,@PathVariable String detailCode) {				
																		
		service.deleteBizDetailCode(new BizDetailCodeId(typeCode, detailCode));						
								 					
		return ResponseEntityUtil.toList(null											
										,MessageUtil.getDeleteMessage(1));
	}
	
}
