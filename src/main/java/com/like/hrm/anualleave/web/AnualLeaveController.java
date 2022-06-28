package com.like.hrm.anualleave.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.anualleave.boundary.AnualLeaveDTO;
import com.like.hrm.anualleave.domain.model.AnualLeave;
import com.like.hrm.anualleave.service.AnualLeaveService;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class AnualLeaveController {
	
	private AnualLeaveService anualLeaveService;
	
	public AnualLeaveController(AnualLeaveService anualLeaveService) {
		this.anualLeaveService = anualLeaveService;		
	}

	@GetMapping("/hrm/anualleave/{yyyy}/{staffId}")
	public ResponseEntity<?> getAnualLeave(@PathVariable Integer yyyy
									  	  ,@PathVariable String staffId) {
				
		AnualLeave entity = anualLeaveService.getAnualLeave(yyyy, staffId);					
		
		AnualLeaveDTO.SaveAnualLeave dto = AnualLeaveDTO.SaveAnualLeave.convertDTO(entity); 
		
		return ResponseEntityUtil.toOne(dto											
									   ,String.format("%d 건 조회되었습니다.", 1));
	}
		
	@PostMapping("/hrm/anualleave")
	public ResponseEntity<?> saveAnualLeave(@RequestBody AnualLeaveDTO.SaveAnualLeave dto) {							
																	
		anualLeaveService.saveAnualLeave(dto);						
								 					
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/hrm/anualleave/{yyyy}/{staffId}")
	public ResponseEntity<?> deleteLedger(@PathVariable Integer yyyy
		  	  							 ,@PathVariable String staffId) {				
																		
		anualLeaveService.deleteAnualLeave(yyyy, staffId);						
								 					
		return ResponseEntityUtil.toList(null											
										,String.format("%d 건 삭제되었습니다.", 1));
	}
}
