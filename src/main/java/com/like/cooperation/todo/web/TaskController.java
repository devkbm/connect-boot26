package com.like.cooperation.todo.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.boundary.TaskDTO;
import com.like.cooperation.todo.domain.Task;
import com.like.cooperation.todo.service.TaskCommandService;
import com.like.system.core.web.exception.ControllerException;
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
										    ,"생성되었습니다."
										    ,HttpStatus.OK);
	}
		
	@PostMapping("/api/todo/taskgroup")
	public ResponseEntity<?> saveTaskGroup(@RequestBody TaskDTO.FormTaskGroup dto, BindingResult result) {
							
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
			
		taskCommandService.saveTaskGroup(dto);
																				 			
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	@DeleteMapping("/api/todo/taskgroup/{id}")
	public ResponseEntity<?> deleteTerm(@PathVariable("id") Long id) {							
			
		taskCommandService.deleteTaskGroup(id);
											 				
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	@GetMapping("/api/todo/taskgroup/{id}/task")
	public ResponseEntity<?> getTaskList(@PathVariable("id") Long id) {				
		
		List<Task> list = taskCommandService.getTaskGroup(id).getTaskList();
		
		List<TaskDTO.FormTask> dtoList = list.stream().map(e -> TaskDTO.FormTask.convert(e)).collect(Collectors.toList()); 											
		
		return WebControllerUtil.getResponse(dtoList
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
	
	@PostMapping("/api/todo/taskgroup/task")
	public ResponseEntity<?> saveTask(@RequestBody TaskDTO.FormTask dto, BindingResult result) {
							
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
			
		taskCommandService.saveTask(dto);
																				 			
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/api/todo/taskgroup/{id}/task/{id2}")
	public ResponseEntity<?> deleteTask(@PathVariable("id") Long id
									   ,@PathVariable("id") Long id2) {							
			
		taskCommandService.deleteTask(id, id2);
											 				
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
}
