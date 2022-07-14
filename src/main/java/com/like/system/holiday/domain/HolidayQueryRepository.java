package com.like.system.holiday.domain;

import java.time.LocalDate;
import java.util.List;

public interface HolidayQueryRepository {

	List<Holiday> getHoliday(String organizationCode, LocalDate fromDate, LocalDate toDate);
}
