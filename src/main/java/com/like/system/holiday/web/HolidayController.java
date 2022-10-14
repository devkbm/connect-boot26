package com.like.system.holiday.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.holiday.boundary.HolidayDTO;
import com.like.system.holiday.domain.Holiday;
import com.like.system.holiday.domain.HolidayId;
import com.like.system.holiday.service.HolidayService;

@RestController
public class HolidayController {

	private HolidayService holidayService;			
	
	public HolidayController(HolidayService holidayService) {
		this.holidayService = holidayService;			
	}		
	
	@GetMapping("/api/system/holiday/{date}")
	public ResponseEntity<?> getHoliday(@RequestParam String organizationCode,
			                            @PathVariable @DateTimeFormat(pattern="yyyyMMdd") LocalDate date) {
		
		HolidayDTO.Form dto = HolidayDTO.Form.convert(holidayService.getHoliyday(organizationCode,date)
																	.orElse(new Holiday(new HolidayId(organizationCode, date), "", "")));
					
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
		
	@PostMapping("/api/system/holiday")
	public ResponseEntity<?> saveHoliday(@RequestBody HolidayDTO.Form dto) {							
																	
		holidayService.saveHoliday(dto);						
								 					
		return toList(null,MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/system/holiday/{id}")
	public ResponseEntity<?> delHoliday(@RequestParam String organizationCode,
			                            @PathVariable @DateTimeFormat(pattern="yyyyMMdd") LocalDate id) {						
												
		holidayService.deleteHoliday(organizationCode,id);
								 						
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
