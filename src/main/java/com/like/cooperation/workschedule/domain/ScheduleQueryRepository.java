package com.like.cooperation.workschedule.domain;

import java.util.List;

import com.like.cooperation.workschedule.boundary.ScheduleDTO;

public interface ScheduleQueryRepository {

	public List<Schedule> getScheduleList(ScheduleDTO.SearchSchedule searchCondition);
}
