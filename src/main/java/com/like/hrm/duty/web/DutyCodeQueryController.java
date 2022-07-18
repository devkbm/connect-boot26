package com.like.hrm.duty.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.duty.service.DutyCodeQueryService;
import com.like.hrm.dutycode.boundary.DutyCodeDTO;
import com.like.system.core.message.MessageUtil;

@RestController
public class DutyCodeQueryController {

	private DutyCodeQueryService service;
	
	public DutyCodeQueryController(DutyCodeQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/dutycode")
	public ResponseEntity<?> getDutyCodeList(DutyCodeDTO.SearchDutyCode dto) {
								
		
		List<DutyCodeDTO.SaveDutyCode> list = service.getDutyCodeList(dto)
													 .stream()
													 .map(e -> DutyCodeDTO.SaveDutyCode.convert(e))
													 .collect(Collectors.toList());
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
