package com.like.system.holiday.web;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.core.web.util.ResponseEntityUtil;
import com.like.system.holiday.domain.Holiday;
import com.like.system.holiday.service.HolidayService;

@RestController
public class HolidayController {

	private HolidayService holidayService;			
	
	public HolidayController(HolidayService holidayService) {
		this.holidayService = holidayService;			
	}		
	
	@GetMapping("/api/common/holiday/{orgcd}/{id}")
	public ResponseEntity<?> getHoliday(@PathVariable String orgcd,
			                            @PathVariable @DateTimeFormat(pattern="yyyyMMdd") LocalDate id) {
		
		Holiday entity = holidayService.getHoliyday(orgcd,id);
					
		return ResponseEntityUtil.toOne(entity
									   ,MessageUtil.getQueryMessage(entity == null ? 0 : 1));
	}
		
	@PostMapping("/api/common/holiday")
	public ResponseEntity<?> saveHoliday(@RequestBody Holiday dto) {							
																	
		holidayService.saveHoliday(dto);						
								 					
		return ResponseEntityUtil.toList(null											
										,MessageUtil.getSaveMessage(1));
	}
	
	@DeleteMapping("/api/common/holiday/{orgcd}/{id}")
	public ResponseEntity<?> delHoliday(@PathVariable String orgcd,
			                            @PathVariable @DateTimeFormat(pattern="yyyyMMdd") LocalDate id) {						
												
		holidayService.deleteHoliday(orgcd,id);
								 						
		return ResponseEntityUtil.toList(null											
										,MessageUtil.getDeleteMessage(1));
	}
}
