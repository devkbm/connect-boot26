package com.like.cooperation.todo.boundary;

import java.time.LocalDate;

import org.springframework.util.StringUtils;

import com.like.cooperation.todo.domain.QTodo;
import com.like.cooperation.todo.domain.QTodoGroup;
import com.like.cooperation.todo.domain.Todo;
import com.like.cooperation.todo.domain.TodoGroup;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class TodoDTO {

	public record FormTodoGroup(
			Long pkTodoGroup,
			String todoGroupName
			) {
		
		public void modifyEntity(TodoGroup entity) {
			entity.modify(todoGroupName);
		}
	}
	
	public record SearchTodo(
			String userId,
			String todo,
			Boolean isCompleted,
			LocalDate dueDate
			) {
		private static final QTodoGroup qTaskGroup = QTodoGroup.todoGroup;
		private static final QTodo qTask = QTodo.todo1;		
		
		public BooleanBuilder getQueryFilter() {		
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeMenGroupCode(todo));
			
			if (StringUtils.hasText(this.userId))
				builder.and(qTaskGroup.modifiedBy.eq(userId));
			
			if (StringUtils.hasText(this.todo))
				builder.and(qTask.todo.like("%"+todo+"%"));
			
			if (this.isCompleted != null)
				builder.and(qTask.isCompleted.eq(isCompleted));				 		
			
			return builder;
		}
		
		private BooleanExpression likeMenGroupCode(String todo) {
			if (!StringUtils.hasText(todo)) return null;
						
			return qTask.todo.like("%"+todo+"%");
		}
	}
		
	@Builder
	public static record FormTodo(
			Long pkTodoGroup,
			Long pkTodo,
			String todo,
			boolean isCompleted,
			LocalDate dueDate,
			String comments
			) {
		
		public Todo newEntity(TodoGroup todoGroup) {
			return Todo.builder()
					   .todoGroup(todoGroup)
					   .todo(todo)
					   .dueDate(dueDate)
					   .comments(comments)
					   .build();	
		}
		
		public void modifyEntity(Todo entity) {
			entity.modify(todo, isCompleted, dueDate, comments);
		}
		
		public static FormTodo convert(Todo entity) {
			return FormTodo.builder()
					       .pkTodoGroup(entity.getTodoGroup().getPkTodoGroup())
					       .pkTodo(entity.getPkTodo())
					       .todo(entity.getTodo())
					       .isCompleted(entity.isCompleted())
					       .dueDate(entity.getDueDate())
					       .comments(entity.getComments())
						   .build();	
		}
	}	
	
}
