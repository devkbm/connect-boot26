package com.like.cooperation.todo.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.domain.TodoGroup;
import com.like.cooperation.todo.service.TodoQueryService;
import com.like.system.core.util.SessionUtil;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class TodoQueryController {

	private TodoQueryService service;
	
	public TodoQueryController(TodoQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/todo/group/mylist")
	public ResponseEntity<?> getMyTodoGroupList() {
						
		String userId = SessionUtil.getUserId();
		
		List<TodoGroup> list = service.getTodoGroupList(userId);			 					
		
		return ResponseEntityUtil.toList(list
										,String.format("%d 건 조회되었습니다.", list.size()));
	}
	
}
