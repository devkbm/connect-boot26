package com.like.cooperation.todo.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.boundary.TodoDTO;
import com.like.cooperation.todo.domain.Todo;
import com.like.cooperation.todo.service.TodoCommandService;
import com.like.system.core.web.util.WebControllerUtil;

@RestController
public class TodoController {	
		
	private TodoCommandService taskCommandService;

	public TodoController(TodoCommandService taskCommandService) {		
		this.taskCommandService = taskCommandService;
	}		
		
	@PostMapping("/api/todo/group/new")
	public ResponseEntity<?> newTodoGroup() {
										
		taskCommandService.newDefaultTodoGroup();										
								 					
		return WebControllerUtil.getResponse(null
										    ,"생성되었습니다.");
	}
		
	@PostMapping("/api/todo/group")
	public ResponseEntity<?> saveTaskGroup(@RequestBody @Valid TodoDTO.FormTodoGroup dto) {								
			
		taskCommandService.saveTodoGroup(dto);
																				 			
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 저장되었습니다.", 1));
	}
		
	@DeleteMapping("/api/todo/group/{id}")
	public ResponseEntity<?> deleteTodoGroup(@PathVariable Long id) {							
			
		taskCommandService.deleteTodoGroup(id);
											 				
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 삭제되었습니다.", 1));
	}
		
	@GetMapping("/api/todo/group/{id}/list")
	public ResponseEntity<?> getTaskList(@PathVariable Long id) {				
		
		List<Todo> list = taskCommandService.getTodoGroup(id).getTodoList();
		
		List<TodoDTO.FormTodo> dtoList = list.stream()
											 .map(e -> TodoDTO.FormTodo.convert(e))
											 .toList(); 											
		
		return WebControllerUtil.getResponse(dtoList
											,String.format("%d 건 조회되었습니다.", dtoList.size()));
	}
	
	@PostMapping("/api/todo/group/todo")
	public ResponseEntity<?> saveTask(@RequestBody @Valid TodoDTO.FormTodo dto) {								
			
		taskCommandService.saveTodo(dto);
																				 			
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/api/todo/group/{todogroupid}/todo/{todoid}")
	public ResponseEntity<?> deleteTask(@PathVariable Long todogroupid
									   ,@PathVariable Long todoid) {							
			
		taskCommandService.deleteTodo(todogroupid, todoid);
											 				
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 삭제되었습니다.", 1));
	}
	
}