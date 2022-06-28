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
import com.like.cooperation.todo.domain.TodoGroup;
import com.like.cooperation.todo.service.TodoCommandService;
import com.like.system.core.web.util.ResponseEntityUtil;

@RestController
public class TodoController {	
		
	private TodoCommandService taskCommandService;

	public TodoController(TodoCommandService taskCommandService) {		
		this.taskCommandService = taskCommandService;
	}		
		
	@PostMapping("/api/todo/group/new")
	public ResponseEntity<?> newTodoGroup() {
										
		TodoGroup taskGroup = taskCommandService.newDefaultTodoGroup();										
								 					
		return ResponseEntityUtil.toOne(taskGroup
									   ,"생성되었습니다.");
	}
		
	@PostMapping("/api/todo/group")
	public ResponseEntity<?> saveTodoGroup(@RequestBody @Valid TodoDTO.FormTodoGroup dto) {								
			
		taskCommandService.saveTodoGroup(dto);
																				 			
		return ResponseEntityUtil.toList(null
										,String.format("%d 건 저장되었습니다.", 1));
	}
		
	@DeleteMapping("/api/todo/group/{id}")
	public ResponseEntity<?> deleteTodoGroup(@PathVariable Long id) {							
			
		taskCommandService.deleteTodoGroup(id);
											 				
		return ResponseEntityUtil.toList(null
										,String.format("%d 건 삭제되었습니다.", 1));
	}
		
	@GetMapping("/api/todo/group/{id}/list")
	public ResponseEntity<?> getTodoList(@PathVariable Long id) {				
		
		List<Todo> list = taskCommandService.getTodoGroup(id).getTodoList();
		
		List<TodoDTO.FormTodo> dtoList = list.stream()
											 .map(e -> TodoDTO.FormTodo.convert(e))
											 .toList(); 											
		
		return ResponseEntityUtil.toList(dtoList
										,String.format("%d 건 조회되었습니다.", dtoList.size()));
	}
	
	@PostMapping("/api/todo/group/todo")
	public ResponseEntity<?> saveTodo(@RequestBody @Valid TodoDTO.FormTodo dto) {								
			
		TodoDTO.FormTodo todo = TodoDTO.FormTodo.convert(taskCommandService.saveTodo(dto));
																				 			
		return ResponseEntityUtil.toOne(todo
									   ,String.format("%d 건 저장되었습니다.", 1));
	}
	
	@DeleteMapping("/api/todo/group/{groupid}/todo/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long groupid
									   ,@PathVariable Long id) {							
			
		taskCommandService.deleteTodo(groupid, id);
											 				
		return ResponseEntityUtil.toList(null
										,String.format("%d 건 삭제되었습니다.", 1));
	}
	
}
