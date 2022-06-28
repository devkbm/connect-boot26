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

import com.like.system.core.web.util.WebResponseUtil;
import com.like.system.holiday.domain.Holiday;
import com.like.system.holiday.service.HolidayService;

@RestController
public class HolidayController {

	private HolidayService holidayService;			
	
	public HolidayController(HolidayService holidayService) {
		this.holidayService = holidayService;			
	}		
	
	@GetMapping("/api/common/holiday/{id}")
	public ResponseEntity<?> getHoliday(@PathVariable @DateTimeFormat(pattern="yyyyMMdd") LocalDate id) {
		
		Holiday entity = holidayService.getHoliyday(id);
					
		return WebResponseUtil.toOne(entity											
											,String.format("%d 건 조회되었습니다.", entity == null ? 0 : 1));
	}
		
	@PostMapping("/api/common/holiday")
	public ResponseEntity<?> saveHoliday(@RequestBody Holiday dto) {							
																	
		holidayService.saveHoliday(dto);						
								 					
		return WebResponseUtil.toList(null											
											,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/api/common/holiday/{id}")
	public ResponseEntity<?> delHoliday(@PathVariable @DateTimeFormat(pattern="yyyyMMdd") LocalDate id) {						
												
		holidayService.deleteHoliday(id);
								 						
		return WebResponseUtil.toList(null											
											,String.format("%d 건 삭제되었습니다.", 1));
	}
}
