package com.like.hrm.staff.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.family.StaffFamily;
import com.like.hrm.staff.service.StaffFamilyService;
import com.like.system.core.message.MessageUtil;


@RestController
public class StaffFamilyController {

	private StaffFamilyService service;
		
	public StaffFamilyController(StaffFamilyService service) {
		this.service = service;	
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/family")
	public ResponseEntity<?> getFamily(@PathVariable String staffId) {
													
		List<StaffDTO.FormFamily> list = service.getFamilyList(staffId)
												.stream()
												.map(e -> StaffDTO.FormFamily.convert(e))
												.toList();
		
		return toOne(list, MessageUtil.getQueryMessage(list.size()));						
	}
	
	
	@GetMapping("/api/hrm/staff/{staffId}/family/{seq}")
	public ResponseEntity<?> getFamily(@PathVariable String staffId
									  ,@PathVariable Long seq) {
						
		StaffFamily entity = service.getFamily(staffId, seq);  									
				
		StaffDTO.FormFamily dto = StaffDTO.FormFamily.convert(entity) ;
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));							
	}
		
	@PostMapping("/api/hrm/staff/family")
	public ResponseEntity<?> saveFamily(@Valid @RequestBody StaffDTO.FormFamily dto) {			
							
		service.saveFamily(dto);
											 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
