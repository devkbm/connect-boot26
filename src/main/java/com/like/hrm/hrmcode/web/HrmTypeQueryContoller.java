package com.like.hrm.hrmcode.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.boundary.HrmCodeTypeDTO;
import com.like.hrm.hrmcode.boundary.HrmCodeDTO;
import com.like.hrm.hrmcode.service.HrmTypeQueryService;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmTypeQueryContoller {

	private HrmTypeQueryService service;
	
	public HrmTypeQueryContoller(HrmTypeQueryService service) {
		this.service = service;
	}	
	
	@GetMapping("/api/hrm/hrmtype")
	public ResponseEntity<?> getHrmTypeList(HrmCodeTypeDTO.Search dto) {														
		
		List<HrmCodeTypeDTO.Form> list = service.getHrmTypeList(dto)
										    .stream()
										    .map(e -> HrmCodeTypeDTO.Form.convert(e))
										    .toList();							
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/hrmtype/code")
	public ResponseEntity<?> getHrmTypeDetailCodeList(HrmCodeDTO.Search dto) {														
		
		List<HrmCodeDTO.Form> list = service.getTypeDetailCodeList(dto)
													  .stream()
													  .map(e -> HrmCodeDTO.Form.convert(e))
													  .toList();																	   
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
