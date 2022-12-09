package com.like.hrm.duty.domain.model;

import lombok.Getter;

@Getter
public enum DutyCode {
		
	A01("연차", 8d, false, false, "연차"),
	A02("반차", 4d, false, false, "반차"),
	A03("반반차", 2d, false, false, "반반차"),
	A04("시간차", 1d, true, false, "직원이 1시간이상 8시간 미만으로 조정하여 사용"),
	
	B01("경조휴가", 8d, false, true, "경조휴가"),
	
	C01("휴직", 8d, false, true, "휴직")
	;
		
	private String name;
	private Double defaultHour;
	private Boolean isHourUIModify;
	private Boolean isIncludingHolidays;
	private String description;
	
	private DutyCode(String name
			        ,Double defaultHour
			        ,Boolean isUIModify
			        ,Boolean isIncludingHolidays
			        ,String description) {
		this.name = name;
		this.defaultHour = defaultHour;
	    this.isHourUIModify = isUIModify;
	    this.isIncludingHolidays = isIncludingHolidays;
		this.description = description;		
	}
		
}
