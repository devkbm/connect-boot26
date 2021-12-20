package com.like.cooperation.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.todo.domain.QTaskGroup;
import com.like.cooperation.todo.domain.TaskGroup;
import com.like.cooperation.todo.domain.TaskGroupRepository;

@Service
@Transactional(readOnly=true)
public class TaskQueryService {
	
	private TaskGroupRepository repository;
	
	public TaskQueryService(TaskGroupRepository repository) {
		this.repository = repository;
	}
			
	public List<TaskGroup> getTaskGroupList(String userId) {
		QTaskGroup qTaskGroup = QTaskGroup.taskGroup;
		
		Iterable<TaskGroup> result = repository.findAll(qTaskGroup.createdBy.eq(userId)); 
		List<TaskGroup> list = new ArrayList<>();
		result.forEach(e -> list.add(e));
		
		return list;
	}
		
}
