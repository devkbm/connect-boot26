package com.like.cooperation.todo.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.boundary.TaskDTO;
import com.like.cooperation.todo.domain.Task;
import com.like.cooperation.todo.service.TaskCommandService;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class TaskController {	
		
	private TaskCommandService taskCommandService;

	public TaskController(TaskCommandService taskCommandService) {		
		this.taskCommandService = taskCommandService;
	}		
		
	@PostMapping("/api/todo/taskgroup/new")
	public ResponseEntity<?> newTaskGroup() {
										
		taskCommandService.newTaskGroup();										
								 					
		return WebControllerUtil.getResponse(null
										    ,"생성되었습니다.");
	}
		
	@PostMapping("/api/todo/taskgroup")
	public ResponseEntity<?> saveTaskGroup(@RequestBody @Valid TaskDTO.FormTaskGroup dto) {								
			
		taskCommandService.saveTaskGroup(dto);
																				 			
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 저장되었습니다.", 1));
	}
		
	@DeleteMapping("/api/todo/taskgroup/{id}")
	public ResponseEntity<?> deleteTerm(@PathVariable Long id) {							
			
		taskCommandService.deleteTaskGroup(id);
											 				
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 삭제되었습니다.", 1));
	}
		
	@GetMapping("/api/todo/taskgroup/{id}/task")
	public ResponseEntity<?> getTaskList(@PathVariable Long id) {				
		
		List<Task> list = taskCommandService.getTaskGroup(id).getTaskList();
		
		List<TaskDTO.FormTask> dtoList = list.stream().map(e -> TaskDTO.FormTask.convert(e)).collect(Collectors.toList()); 											
		
		return WebControllerUtil.getResponse(dtoList
											,String.format("%d 건 조회되었습니다.", dtoList.size()));
	}
	
	@PostMapping("/api/todo/taskgroup/task")
	public ResponseEntity<?> saveTask(@RequestBody @Valid TaskDTO.FormTask dto) {								
			
		taskCommandService.saveTask(dto);
																				 			
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/api/todo/taskgroup/{id}/task/{id2}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id
									   ,@PathVariable Long id2) {							
			
		taskCommandService.deleteTask(id, id2);
											 				
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 삭제되었습니다.", 1));
	}
	
}
