package com.like.hrm.staff.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.StaffDutyResponsibilityDTO;
import com.like.hrm.staff.domain.model.dutyresponsibility.StaffDuty;
import com.like.hrm.staff.service.StaffDutyResponsibilityService;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffDutyResponsibilityController {

	private StaffDutyResponsibilityService service;
	
	public StaffDutyResponsibilityController(StaffDutyResponsibilityService service) {
		this.service = service;		
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/dutyresponsibility/{seq}")
	public ResponseEntity<?> getAppointmentRecord(@PathVariable String staffId
									  			 ,@PathVariable Integer seq) {
				
		StaffDuty entity = service.get(staffId, seq);  									
				
		var dto = StaffDutyResponsibilityDTO.Form.convert(entity) ;
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
	
	@PostMapping("/api/hrm/staff/{staffId}/dutyresponsibility")
	public ResponseEntity<?> saveAppointmentRecord(@Valid @RequestBody StaffDutyResponsibilityDTO.Form dto) {			
									
		service.save(dto);
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
