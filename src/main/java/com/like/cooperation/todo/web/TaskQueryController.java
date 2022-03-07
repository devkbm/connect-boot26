package com.like.cooperation.todo.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.domain.TaskGroup;
import com.like.cooperation.todo.service.TaskQueryService;
import com.like.system.core.util.SessionUtil;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class TaskQueryController {

	private TaskQueryService service;
	
	public TaskQueryController(TaskQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/todo/taskgroup/mylist")
	public ResponseEntity<?> getTaskGroupList() {
						
		String userId = SessionUtil.getUserId();
		
		List<TaskGroup> list = service.getTaskGroupList(userId);			 					
		
		return WebControllerUtil.getResponse(list
											,String.format("%d 건 조회되었습니다.", list.size()));
	}
	
}
