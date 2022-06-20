package com.like.cooperation.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.todo.domain.QTodoGroup;
import com.like.cooperation.todo.domain.TodoGroup;
import com.like.cooperation.todo.domain.TodoGroupRepository;

@Service
@Transactional(readOnly=true)
public class TodoQueryService {
	
	private TodoGroupRepository repository;
	
	public TodoQueryService(TodoGroupRepository repository) {
		this.repository = repository;
	}
			
	public List<TodoGroup> getTodoGroupList(String userId) {
		QTodoGroup qTodoGroup = QTodoGroup.todoGroup;
		
		//Iterable<TodoGroup> result = repository.findAll(qTodoGroup.createdBy.createdBy.eq(userId)); 
		List<TodoGroup> list = new ArrayList<>();
		//result.forEach(e -> list.add(e));
		
		return list;
	}
		
}
