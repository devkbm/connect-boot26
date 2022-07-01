package com.like.hrm.staff.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.AppointmentRecordDTO;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;
import com.like.hrm.staff.service.StaffAppointmentService;
import com.like.system.core.message.MessageUtil;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class StaffAppointmentController {

	private StaffAppointmentService service;
	
	public StaffAppointmentController(StaffAppointmentService service) {
		this.service = service;
	}
	
	@GetMapping("/hrm/staff/{staffId}/appointmentrecord")
	public ResponseEntity<?> getAppointmentRecordList(@PathVariable String staffId) {
										
		List<AppointmentRecordDTO.FormStaffAppointmentRecord> list = service.getAppointmentRecord(staffId)
																			.stream()
																			.map(e -> AppointmentRecordDTO.FormStaffAppointmentRecord.convert(e))
																			.toList(); 		
		
		return ResponseEntityUtil.toList(list
										,MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/hrm/staff/{staffId}/appointmentrecord/{id}")
	public ResponseEntity<?> getAppointmentRecord(@PathVariable String staffId
									  			 ,@PathVariable Long id) {
				
		AppointmentRecord entity = service.getAppointmentRecord(staffId, id);  									
				
		var dto = AppointmentRecordDTO.FormStaffAppointmentRecord.convert(entity) ;
		
		return ResponseEntityUtil.toOne(dto
									   ,MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
		
	@PostMapping("/hrm/staff/{staffId}/appointmentrecord")
	public ResponseEntity<?> saveAppointmentRecord(@Valid @RequestBody AppointmentRecordDTO.FormStaffAppointmentRecord dto) {			
									
		service.saveAppointmentRecord(dto);
											 				
		return ResponseEntityUtil.toList(null
										,MessageUtil.getSaveMessage(1));
	}
	
	@GetMapping("/hrm/staff/{staffId}/appointmentrecord/{id}/apply")
	//@RequestMapping(value={"/hrm/staff/{staffId}/appointmentrecord/{id}/apply"}, method={RequestMethod.POST})	
	public ResponseEntity<?> applyAppointmentRecord(@PathVariable String staffId
 			 									   ,@PathVariable Long id) {									
						
		service.applyAppointmentRecord(staffId, id);
											 				
		return ResponseEntityUtil.toList(null
										,"발령처리되었습니다.");
	}
}
