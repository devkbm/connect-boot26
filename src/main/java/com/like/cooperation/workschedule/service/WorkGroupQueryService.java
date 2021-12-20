package com.like.cooperation.workschedule.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.workschedule.boundary.WorkDTO;
import com.like.cooperation.workschedule.domain.WorkGroup;
import com.like.cooperation.workschedule.domain.WorkGroupQueryRepository;


@Service
@Transactional(readOnly=true)
public class WorkGroupQueryService {
				
	private WorkGroupQueryRepository repository;
	
	public WorkGroupQueryService(WorkGroupQueryRepository repository) {		
		this.repository = repository;
	}
	
	public List<WorkGroup> getWorkGroupList(WorkDTO.SearchWorkGroup searchCondition) {
		return repository.getWorkGroupList(searchCondition);		
	}
			
	public List<WorkGroup> getMyWorkGroupList(String userId) {
		return repository.getWorkGroupList(userId);
	}
}
