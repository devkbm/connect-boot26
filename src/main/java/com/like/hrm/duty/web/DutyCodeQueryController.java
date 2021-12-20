package com.like.hrm.duty.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.duty.service.DutyCodeQueryService;
import com.like.hrm.dutycode.boundary.DutyCodeDTO;
import com.like.hrm.dutycode.domain.DutyCode;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class DutyCodeQueryController {

	private DutyCodeQueryService service;
	
	public DutyCodeQueryController(DutyCodeQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/hrm/dutycode")
	public ResponseEntity<?> getDutyCodeList(DutyCodeDTO.SearchDutyCode dto) {
		
		List<DutyCode> list = service.getDutyCodeList(dto);					
		
		List<DutyCodeDTO.SaveDutyCode> dtoList = list.stream()
													 .map(e -> DutyCodeDTO.SaveDutyCode.convert(e))
													 .collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
}
