package com.like.hrm.staff.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.FamilyDTO;
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
	public ResponseEntity<?> getFamilyList(@PathVariable String staffId) {
													
		List<FamilyDTO.Form> list = service.getFamilyList(staffId)
												.stream()
												.map(e -> FamilyDTO.Form.convert(e))
												.toList();
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));						
	}
	
	
	@GetMapping("/api/hrm/staff/{staffId}/family/{seq}")
	public ResponseEntity<?> getFamily(@PathVariable String staffId
									  ,@PathVariable Long seq) {
						
		StaffFamily entity = service.getFamily(staffId, seq);  									
				
		FamilyDTO.Form dto = FamilyDTO.Form.convert(entity) ;
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));							
	}
		
	@PostMapping("/api/hrm/staff/{staffId}/family")
	public ResponseEntity<?> saveFamily(@Valid @RequestBody FamilyDTO.Form dto) {			
							
		service.saveFamily(dto);
											 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/hrm/staff/{staffId}/family/{seq}")
	public ResponseEntity<?> deleteFamily(@PathVariable String staffId
									     ,@PathVariable Long seq) {
						
		service.deleteFamily(staffId, seq);  									
		
		return toOne(null, MessageUtil.getDeleteMessage(1));									
	}
}
