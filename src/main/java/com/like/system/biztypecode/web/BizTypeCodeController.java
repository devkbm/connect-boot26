package com.like.system.biztypecode.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

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

@RestController
public class BizTypeCodeController {

	private BizTypeCodeService service;
	
	public BizTypeCodeController(BizTypeCodeService service) {
		this.service = service;
	}
	
	@GetMapping("/api/system/biztype/{id}")
	public ResponseEntity<?> getBizTypeCode(@PathVariable String id) {
		
		BizTypeCodeDTO.FormBizTypeCode dto = BizTypeCodeDTO.FormBizTypeCode.convert(service.getBizTypeCode(id));
					
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
			
	@PostMapping("/api/system/biztype")	
	public ResponseEntity<?> saveHrmType(@RequestBody BizTypeCodeDTO.FormBizTypeCode dto) {				
																			
		service.saveBizTypeCode(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
		
	@DeleteMapping("/api/system/biztype/{id}")
	public ResponseEntity<?> deleteHrmType(@PathVariable String id) {				
																		
		service.deleteBizTypeCode(id);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
	@GetMapping("/api/common/biztype/{typeCode}/bizdetail/{detailCode}")
	public ResponseEntity<?> getBizDetailCode(@PathVariable String typeCode
											 ,@PathVariable String detailCode) {
		
		BizDetailCodeDTO.FormBizDetailCode dto = BizDetailCodeDTO.FormBizDetailCode.convert(service.getBizDetailCode(new BizDetailCodeId(typeCode, detailCode)));
					
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
			
	@PostMapping("/api/system/biztype/bizdetail")	
	public ResponseEntity<?> saveBizDetailCode(@RequestBody BizDetailCodeDTO.FormBizDetailCode dto) {				
																		
		service.saveBizDetailCode(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
		
	@DeleteMapping("/api/system/biztype/{typeCode}/bizdetail/{detailCode}")
	public ResponseEntity<?> deleteBizDetailCode(@PathVariable String typeCode
			 									,@PathVariable String detailCode) {				
																		
		service.deleteBizDetailCode(new BizDetailCodeId(typeCode, detailCode));						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
}
