package com.like.hrm.staff.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecordList;
import com.like.hrm.staff.service.StaffAppointmentService;
import com.like.system.core.web.exception.ControllerException;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class StaffAppointmentController {

	private StaffAppointmentService service;
	
	public StaffAppointmentController(StaffAppointmentService service) {
		this.service = service;
	}
	
	@GetMapping("/hrm/staff/{staffId}/appointmentrecord")
	public ResponseEntity<?> getAppointmentRecordList(@PathVariable String staffId) {
				
		AppointmentRecordList entity = service.getAppointmentRecord(staffId);  									
				
		List<StaffDTO.FormStaffAppointmentRecord> list = entity.getStream()
														  .map(e -> StaffDTO.FormStaffAppointmentRecord.convert(e))
														  .collect(Collectors.toList()); 		
		
		return WebControllerUtil
				.getResponse(list											
							,String.format("%d 건 조회되었습니다.", list.size())
							,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/staff/{staffId}/appointmentrecord/{id}")
	public ResponseEntity<?> getAppointmentRecord(@PathVariable String staffId
									  			 ,@PathVariable Long id) {
				
		AppointmentRecord entity = service.getAppointmentRecord(staffId, id);  									
				
		StaffDTO.FormStaffAppointmentRecord dto = StaffDTO.FormStaffAppointmentRecord.convert(entity) ;
		
		return WebControllerUtil
				.getResponse(dto											
							,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/staff/{staffId}/appointmentrecord"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveAppointmentRecord(@RequestBody StaffDTO.FormStaffAppointmentRecord dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류 : " +dto.toString());
		} 											
				
		service.saveAppointmentRecord(dto);
											 				
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/staff/{staffId}/appointmentrecord/{id}/apply")
	//@RequestMapping(value={"/hrm/staff/{staffId}/appointmentrecord/{id}/apply"}, method={RequestMethod.POST})	
	public ResponseEntity<?> applyAppointmentRecord(@PathVariable String staffId
 			 									   ,@PathVariable Long id) {									
		
		System.out.println(staffId + " : " + id.toString());
		
		service.applyAppointmentRecord(staffId, id);
											 				
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}
}
