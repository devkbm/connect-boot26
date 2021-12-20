package com.like.system.biztypecode.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.biztypecode.boundary.BizTypeCodeDTO;
import com.like.system.biztypecode.service.BizTypeCodeService;
import com.like.system.core.web.exception.ControllerException;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class BizTypeCodeController {

	private BizTypeCodeService service;
	
	public BizTypeCodeController(BizTypeCodeService service) {
		this.service = service;
	}
	
	@GetMapping("/common/biztype/{id}")
	public ResponseEntity<?> getBizTypeCode(@PathVariable(value="id") String id) {
		
		BizTypeCodeDTO.FormBizTypeCode dto = BizTypeCodeDTO.FormBizTypeCode.convert(service.getBizTypeCode(id));
					
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
			
	@PostMapping("/common/biztype")	
	public ResponseEntity<?> saveHrmType(@RequestBody BizTypeCodeDTO.FormBizTypeCode dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		service.saveBizTypeCode(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
		
	@DeleteMapping("/common/biztype/{id}")
	public ResponseEntity<?> deleteHrmType(@PathVariable(value="id") String id) {				
																		
		service.deleteBizTypeCode(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
}
