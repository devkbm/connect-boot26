package com.like.cooperation.todo.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.domain.TodoGroup;
import com.like.cooperation.todo.service.TodoQueryService;
import com.like.system.core.message.MessageUtil;
import com.like.system.core.util.SessionUtil;

@RestController
public class TodoQueryController {

	private TodoQueryService service;
	
	public TodoQueryController(TodoQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/todo/group/mylist")
	public ResponseEntity<?> getMyTodoGroupList() {
										
		List<TodoGroup> list = service.getTodoGroupList(SessionUtil.getUserId());			 					
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
}
