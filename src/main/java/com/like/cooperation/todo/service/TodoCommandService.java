package com.like.cooperation.todo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.todo.boundary.TodoDTO;
import com.like.cooperation.todo.domain.Todo;
import com.like.cooperation.todo.domain.TodoGroup;
import com.like.cooperation.todo.domain.TodoGroupRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class TodoCommandService {
		
	private TodoGroupRepository repository;
	
	public TodoCommandService(TodoGroupRepository repository) {
		this.repository = repository;		
	}
	
	public TodoGroup newDefaultTodoGroup() {
		TodoGroup taskGroup = new TodoGroup();
		repository.save(taskGroup);
		
		return taskGroup;
	}
	
	public TodoGroup getTodoGroup(Long id) {		
		return repository.findById(id).orElse(null);	
	}
		
	public void saveTodoGroup(TodoDTO.FormTodoGroup dto) {
		TodoGroup entity = repository.findById(dto.pkTodoGroup()).orElse(null);
		
		entity.modify(dto.todoGroupName());
		
		repository.save(entity);	
	}	
		
	public void deleteTodoGroup(Long pkTaskGroup) {
		repository.deleteById(pkTaskGroup);		
	}	
	
	public Todo saveTodo(TodoDTO.FormTodo dto) {
		TodoGroup todoGroup = repository.findById(dto.pkTodoGroup()).orElse(null);
		Todo entity = null;
		
		if (dto.pkTodo() == null) {			
			todoGroup.addTodo(dto.newEntity(todoGroup));
		} else {
			entity = todoGroup.getTodo(dto.pkTodo());
			dto.modifyEntity(entity);
		}
					
		repository.save(todoGroup);			
		
		return todoGroup.getLastTodo();
	}
	
	public void deleteTodo(Long pkTodoGroup, Long pkTodo) {
		TodoGroup todoGroup = repository.findById(pkTodoGroup).orElse(null);	
		todoGroup.removeTodo(pkTodo);
		repository.save(todoGroup);
	}
}
