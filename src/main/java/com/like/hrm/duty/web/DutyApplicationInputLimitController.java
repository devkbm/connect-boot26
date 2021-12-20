package com.like.hrm.duty.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.duty.boundary.DutyApplicationInputLimitRuleDTO;
import com.like.hrm.duty.domain.model.DutyApplicationInputLimitRule;
import com.like.hrm.duty.service.DutyApplicationInputLimitRuleService;
import com.like.system.core.web.exception.ControllerException;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class DutyApplicationInputLimitController {
	
	private DutyApplicationInputLimitRuleService service;
			
	public DutyApplicationInputLimitController(DutyApplicationInputLimitRuleService service) {		
		this.service = service;		
	}
	
	@GetMapping("/hrm/dutyapplication/limit")
	public ResponseEntity<?> getDutyApplicationLimitList() {
		
		List<DutyApplicationInputLimitRule> entityList = service.getDutyApplicationInputLimitRule();					
					
		List<DutyApplicationInputLimitRuleDTO.SaveDutyApplicationInputLimitRule> list = entityList.stream()
																								  .map(e -> DutyApplicationInputLimitRuleDTO.SaveDutyApplicationInputLimitRule.convert(e))
																								  .collect(Collectors.toList());
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	
	@GetMapping("/hrm/dutyapplication/limit/{id}")
	public ResponseEntity<?> getDutyApplicationLimit(@PathVariable(value="id") Long id) {
		
		DutyApplicationInputLimitRule entity = service.getDutyApplicationInputLimitRule(id);					
		
		DutyApplicationInputLimitRuleDTO.SaveDutyApplicationInputLimitRule dto = DutyApplicationInputLimitRuleDTO.SaveDutyApplicationInputLimitRule.convert(entity); 
		
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/dutyapplication/limit"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveDutyApplicationLimit(@RequestBody DutyApplicationInputLimitRuleDTO.SaveDutyApplicationInputLimitRule dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		service.saveDutyApplicationInputLimitRule(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/dutyapplication/limit/{id}")
	public ResponseEntity<?> deleteDutyApplicationLimit(@PathVariable(value="id") Long id) {				
																		
		service.deleteDutyApplicationInputLimitRule(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
}
