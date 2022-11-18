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

import com.like.hrm.staff.boundary.AppointmentRecordDTO;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;
import com.like.hrm.staff.service.StaffAppointmentService;
import com.like.system.core.message.MessageUtil;

@RestController
public class StaffAppointmentController {

	private StaffAppointmentService service;
	
	public StaffAppointmentController(StaffAppointmentService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/appointmentrecord")
	public ResponseEntity<?> getAppointmentRecordList(@PathVariable String staffId) {
										
		List<AppointmentRecordDTO.FormStaffAppointmentRecord> list = service.getAppointmentRecord(staffId)
																			.getStream()
																			.map(e -> AppointmentRecordDTO.FormStaffAppointmentRecord.convert(e))
																			.toList(); 		
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/appointmentrecord/{id}")
	public ResponseEntity<?> getAppointmentRecord(@PathVariable String staffId
									  			 ,@PathVariable Long id) {
				
		AppointmentRecord entity = service.getAppointmentRecord(staffId, id);  									
				
		var dto = AppointmentRecordDTO.FormStaffAppointmentRecord.convert(entity) ;
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
		
	@PostMapping("/api/hrm/staff/{staffId}/appointmentrecord")
	public ResponseEntity<?> saveAppointmentRecord(@Valid @RequestBody AppointmentRecordDTO.FormStaffAppointmentRecord dto) {			
									
		service.saveAppointmentRecord(dto);
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/appointmentrecord/{id}/apply")
	//@RequestMapping(value={"/hrm/staff/{staffId}/appointmentrecord/{id}/apply"}, method={RequestMethod.POST})	
	public ResponseEntity<?> applyAppointmentRecord(@PathVariable String staffId
 			 									   ,@PathVariable Long id) {									
						
		service.applyAppointmentRecord(staffId, id);
											 				
		return toList(null, "발령처리되었습니다.");
	}
}
