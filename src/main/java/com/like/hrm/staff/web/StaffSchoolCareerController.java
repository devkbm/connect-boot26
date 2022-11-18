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

import com.like.hrm.staff.boundary.SchoolCareerDTO;
import com.like.hrm.staff.domain.model.schoolcareer.StaffSchoolCareer;
import com.like.hrm.staff.service.StaffSchoolCareerService;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffSchoolCareerController {

	private StaffSchoolCareerService service;
		
	public StaffSchoolCareerController(StaffSchoolCareerService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/schoolcareer")
	public ResponseEntity<?> getSchoolCareer(@PathVariable String staffId) {			
		List<SchoolCareerDTO.Form> list = service.getSchoolCareerList(staffId)
													  .stream()
													  .map(e -> SchoolCareerDTO.Form.convert(e))
													  .toList();
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/schoolcareer/{seq}")
	public ResponseEntity<?> getSchoolCareer(@PathVariable String staffId
											,@PathVariable Long seq) {			
		StaffSchoolCareer schoolCareer = service.getSchoolCareer(staffId, seq);  									
		
		SchoolCareerDTO.Form dto = SchoolCareerDTO.Form.convert(schoolCareer);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/staff/{staffId}/schoolcareer")
	public ResponseEntity<?> saveSchoolCareer(@RequestBody @Valid SchoolCareerDTO.Form dto) {
		service.saveSchoolCareer(dto);
											 				
		return toOne(null, MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/hrm/staff/{staffId}/schoolcareer/{seq}")
	public ResponseEntity<?> deleteSchoolCareer(@PathVariable String staffId
											   ,@PathVariable Long seq) {
		service.deleteSchoolCareer(staffId, seq);
											 				
		return toOne(null, MessageUtil.getDeleteMessage(1));
	}
	
}
