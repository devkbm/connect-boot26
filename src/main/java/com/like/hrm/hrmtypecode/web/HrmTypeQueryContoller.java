package com.like.hrm.hrmtypecode.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmtypecode.boundary.HrmTypeDTO;
import com.like.hrm.hrmtypecode.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.hrmtypecode.domain.AppointmentTypeEnum;
import com.like.hrm.hrmtypecode.service.HrmTypeQueryService;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmTypeQueryContoller {

	private HrmTypeQueryService service;
	
	public HrmTypeQueryContoller(HrmTypeQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/typelist")
	public ResponseEntity<?> getTypeList() {
		
		List<HrmTypeDTO.FormHrmType> list = new ArrayList<HrmTypeDTO.FormHrmType>();
		
		for (AppointmentTypeEnum menuType : AppointmentTypeEnum.values()) {			
			list.add(new HrmTypeDTO.FormHrmType(menuType.getCode(),menuType.getName(),true,0,"HRMTYPE",""));
		}										
					
		return toList(list ,MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/hrmtype")
	public ResponseEntity<?> getHrmTypeList(HrmTypeDTO.SearchHrmType dto) {														
		
		List<HrmTypeDTO.FormHrmType> list = service.getHrmTypeList(dto)
												   .stream()
												   .map(e -> HrmTypeDTO.FormHrmType.convert(e))
												   .toList();												   		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/hrmtype/code")
	public ResponseEntity<?> getHrmTypeDetailCodeList(HrmTypeDetailCodeDTO.SearchHrmTypeDetailCode dto) {														
		
		List<HrmTypeDetailCodeDTO.FormHrmTypeDetailCode> list = service.getTypeDetailCodeList(dto)
																	   .stream()
																	   .map(e -> HrmTypeDetailCodeDTO.FormHrmTypeDetailCode.convert(e))
																	   .toList();																	   
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
