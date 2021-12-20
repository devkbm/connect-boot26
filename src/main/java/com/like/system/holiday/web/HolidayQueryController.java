package com.like.system.holiday.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.web.util.WebControllerUtil;
import com.like.system.holiday.domain.service.DateInfo;
import com.like.system.holiday.service.DateInfoService;

@RestController
public class HolidayQueryController {

	private DateInfoService holidayUtilService;
	
	public HolidayQueryController(DateInfoService holidayUtilService) {
		this.holidayUtilService = holidayUtilService;
	}
		
	@GetMapping("/api/common/holiday")
	public ResponseEntity<?> getHolidayList(@RequestParam @DateTimeFormat(pattern="yyyyMMdd") LocalDate fromDate
										   ,@RequestParam @DateTimeFormat(pattern="yyyyMMdd") LocalDate toDate) {
		
		List<DateInfo> list = holidayUtilService.getDateInfos(fromDate, toDate).getDates();			
					
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
}
