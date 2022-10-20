package com.like.system.biztypecode.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.biztypecode.boundary.BizCodeTypeDTO;
import com.like.system.biztypecode.domain.BizTypeEnum;
import com.like.system.biztypecode.service.BizCodeTypeService;
import com.like.system.core.dto.HtmlSelectOptionRecord;
import com.like.system.core.dto.HtmlSelectOptionable;
import com.like.system.core.message.MessageUtil;

@RestController
public class BizCodeTypeController {

	private BizCodeTypeService service;
	
	public BizCodeTypeController(BizCodeTypeService service) {
		this.service = service;
	}
	
	@GetMapping("/api/system/bizcodetype/system")
	public ResponseEntity<?> getSystemList() {				
		
		List<HtmlSelectOptionRecord> list = HtmlSelectOptionable.fromEnum(BizTypeEnum.class);			
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/system/bizcodetype")
	public ResponseEntity<?> getBizCodeTypeList() {
		
		List<BizCodeTypeDTO.Form> list = service.getBizCodeTypeAllList()
											   .stream()
											   .map(e -> BizCodeTypeDTO.Form.convert(e))
											   .toList();											   
					
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	
	@GetMapping("/api/system/bizcodetype/{typeId}")
	public ResponseEntity<?> getBizCodeType(@RequestParam String organizationCode
										   ,@PathVariable String typeId) {
		
		BizCodeTypeDTO.Form dto = BizCodeTypeDTO.Form.convert(service.getBizCodeType(organizationCode, typeId));
					
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
			
	@PostMapping("/api/system/bizcodetype")	
	public ResponseEntity<?> saveBizCodeType(@RequestBody BizCodeTypeDTO.Form dto) {				
																			
		service.saveBizCodeType(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
		
	@DeleteMapping("/api/system/bizcodetype/{typeId}")
	public ResponseEntity<?> deleteBizCodeType(@RequestParam String organizationCode
											  ,@PathVariable String typeId) {				
																		
		service.deleteBizCodeType(organizationCode, typeId);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
