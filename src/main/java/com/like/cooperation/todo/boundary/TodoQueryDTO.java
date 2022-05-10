package com.like.cooperation.todo.boundary;

import java.time.LocalDate;

import org.springframework.util.StringUtils;

import com.like.cooperation.todo.domain.QTodo;
import com.like.cooperation.todo.domain.QTodoGroup;
import com.querydsl.core.BooleanBuilder;

public class TodoQueryDTO {

	private final QTodoGroup qTodoGroup = QTodoGroup.todoGroup;
	private final QTodo qTodo = QTodo.todo1;
	private BooleanBuilder builder = new BooleanBuilder();
		
	String userId;
	
	String todo;		
		
	Boolean isCompleted;
		
    LocalDate dueDate;
	
	public BooleanBuilder getQueryFilter() {		
		
		if (StringUtils.hasText(this.userId))
			builder.and(qTodoGroup.modifiedBy.eq(userId));
		
		if (StringUtils.hasText(this.todo))
			builder.and(qTodo.todo.like("%"+todo+"%"));
		
		if (this.isCompleted != null)
			builder.and(qTodo.isCompleted.eq(isCompleted));				 		
		
		return builder;
	}
}
