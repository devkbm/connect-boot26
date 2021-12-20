package com.like.cooperation.todo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.todo.boundary.TaskDTO;
import com.like.cooperation.todo.domain.Task;
import com.like.cooperation.todo.domain.TaskGroup;
import com.like.cooperation.todo.domain.TaskGroupRepository;

@Service
@Transactional
public class TaskCommandService {
		
	private TaskGroupRepository repository;
	
	public TaskCommandService(TaskGroupRepository repository) {
		this.repository = repository;		
	}
	
	public void newTaskGroup() {
		TaskGroup taskGroup = new TaskGroup("기본 일정");
		repository.save(taskGroup);
	}
	
	public TaskGroup getTaskGroup(Long id) {		
		return repository.findById(id).orElse(null);	
	}
		
	public void saveTaskGroup(TaskDTO.FormTaskGroup dto) {
		TaskGroup entity = repository.findById(dto.getPkTaskGroup()).orElse(null);
		
		entity.modify(dto.getTaskGroupName());
		
		repository.save(entity);	
	}	
		
	public void deleteTaskGroup(Long pkTaskGroup) {
		repository.deleteById(pkTaskGroup);		
	}	
	
	public void saveTask(TaskDTO.FormTask dto) {
		TaskGroup taskGroup = repository.findById(dto.getPkTaskGroup()).orElse(null);
		Task entity = null;
		if (dto.getPkTask() == null) {
			entity = dto.newEntity(taskGroup);
		} else {
			entity = taskGroup.getTask(dto.getPkTask());
			dto.modifyEntity(entity);
		}
		
		repository.save(taskGroup);
	}
	
	public void deleteTask(Long pkTaskGroup, Long pkTask) {
		TaskGroup taskGroup = repository.findById(pkTaskGroup).orElse(null);	
		taskGroup.removeTask(pkTask);
		repository.save(taskGroup);
	}
}
