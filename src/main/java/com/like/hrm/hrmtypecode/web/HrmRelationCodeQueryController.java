package com.like.hrm.hrmtypecode.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmtypecode.boundary.HrmRelationCodeDTO;
import com.like.hrm.hrmtypecode.service.HrmRelationCodeQueryService;
import com.like.system.core.web.util.WebResponseUtil;

@RestController
public class HrmRelationCodeQueryController {

	private HrmRelationCodeQueryService service;
	
	public HrmRelationCodeQueryController(HrmRelationCodeQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/hrm/hrmrelation")
	public ResponseEntity<?> getHrmRelationCode(HrmRelationCodeDTO.SearchHrmRelationCode dto) {
						
		List<?> list = service.getHrmRelationCodeList(dto);
					
		return WebResponseUtil.toList(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
}
