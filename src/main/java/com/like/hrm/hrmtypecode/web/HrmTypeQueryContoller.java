package com.like.hrm.hrmtypecode.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmtypecode.boundary.HrmTypeDTO;
import com.like.hrm.hrmtypecode.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.hrmtypecode.service.HrmTypeQueryService;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmTypeQueryContoller {

	private HrmTypeQueryService service;
	
	public HrmTypeQueryContoller(HrmTypeQueryService service) {
		this.service = service;
	}	
	
	@GetMapping("/api/hrm/hrmtype")
	public ResponseEntity<?> getHrmTypeList(HrmTypeDTO.SearchHrmType dto) {														
		
		List<HrmTypeDTO.Form> list = service.getHrmTypeList(dto)
										    .stream()
										    .map(e -> HrmTypeDTO.Form.convert(e))
										    .toList();							
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/hrmtype/code")
	public ResponseEntity<?> getHrmTypeDetailCodeList(HrmTypeDetailCodeDTO.SearchHrmTypeDetailCode dto) {														
		
		List<HrmTypeDetailCodeDTO.Form> list = service.getTypeDetailCodeList(dto)
													  .stream()
													  .map(e -> HrmTypeDetailCodeDTO.Form.convert(e))
													  .toList();																	   
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
