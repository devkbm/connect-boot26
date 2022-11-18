package com.like.hrm.anualleave.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

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
import com.like.system.core.message.MessageUtil;

@RestController
public class AnualLeaveController {
	
	private AnualLeaveService anualLeaveService;
	
	public AnualLeaveController(AnualLeaveService anualLeaveService) {
		this.anualLeaveService = anualLeaveService;		
	}

	@GetMapping("/api/hrm/staff/{staffId}/anualleave")
	public ResponseEntity<?> getAnualLeave(@PathVariable String staffId) {
				
		//AnualLeave entity = anualLeaveService.getAnualLeave(staffId, yyyy);					
		
		//AnualLeaveDTO.SaveAnualLeave dto = AnualLeaveDTO.SaveAnualLeave.convertDTO(entity); 
		
		List<AnualLeaveDTO.SaveAnualLeave> list = null;
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/anualleave/{yyyy}")
	public ResponseEntity<?> getAnualLeave(@PathVariable String staffId
									  	  ,@PathVariable Integer yyyy) {
				
		AnualLeave entity = anualLeaveService.getAnualLeave(staffId, yyyy);					
		
		AnualLeaveDTO.SaveAnualLeave dto = AnualLeaveDTO.SaveAnualLeave.convertDTO(entity); 
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/anualleave")
	public ResponseEntity<?> saveAnualLeave(@RequestBody AnualLeaveDTO.SaveAnualLeave dto) {							
																	
		anualLeaveService.saveAnualLeave(dto);						
								 					
		return toOne(null, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/hrm/anualleave/{yyyy}/{staffId}")
	public ResponseEntity<?> deleteLedger(@PathVariable String staffId
		  	  							 ,@PathVariable Integer yyyy) {				
																		
		anualLeaveService.deleteAnualLeave(staffId, yyyy);						
								 					
		return toOne(null, MessageUtil.getDeleteMessage(1));
	}
}
