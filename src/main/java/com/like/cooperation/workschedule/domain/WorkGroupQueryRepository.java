package com.like.cooperation.workschedule.domain;

import java.util.List;

import com.like.cooperation.workschedule.boundary.WorkDTO;

public interface WorkGroupQueryRepository {

	public List<WorkGroup> getWorkGroupList(WorkDTO.Search searchCondition);
	
	public List<WorkGroup> getWorkGroupList(String userId);
}
