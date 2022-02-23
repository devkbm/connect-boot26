package com.like.cooperation.todo.boundary;

import java.time.LocalDate;

import org.springframework.util.StringUtils;

import com.like.cooperation.todo.domain.QTask;
import com.like.cooperation.todo.domain.QTaskGroup;
import com.like.cooperation.todo.domain.Task;
import com.like.cooperation.todo.domain.TaskGroup;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class TaskDTO {

	public record FormTaskGroup(
			Long pkTaskGroup,
			String taskGroupName
			) {
		
		public void modifyEntity(TaskGroup entity) {
			entity.modify(taskGroupName);
		}
	}
	
	public record SearchTask(
			String userId,
			String task,
			Boolean isCompleted,
			LocalDate dueDate
			) {
		private static final QTaskGroup qTaskGroup = QTaskGroup.taskGroup;
		private static final QTask qTask = QTask.task1;		
		
		public BooleanBuilder getQueryFilter() {		
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeMenGroupCode(task));
			
			if (StringUtils.hasText(this.userId))
				builder.and(qTaskGroup.modifiedBy.eq(userId));
			
			if (StringUtils.hasText(this.task))
				builder.and(qTask.task.like("%"+task+"%"));
			
			if (this.isCompleted != null)
				builder.and(qTask.isCompleted.eq(isCompleted));				 		
			
			return builder;
		}
		
		private BooleanExpression likeMenGroupCode(String task) {
			if (!StringUtils.hasText(task)) return null;
						
			return qTask.task.like("%"+task+"%");
		}
	}
		
	@Builder
	public static record FormTask(
			Long pkTaskGroup,
			Long pkTask,
			String task,
			boolean isCompleted,
			LocalDate dueDate,
			String comments
			) {
		
		public Task newEntity(TaskGroup taskGroup) {
			return Task.builder()
					   .taskGroup(taskGroup)
					   .task(task)
					   .dueDate(dueDate)
					   .comments(comments)
					   .build();	
		}
		
		public void modifyEntity(Task entity) {
			entity.modify(task, isCompleted, dueDate, comments);
		}
		
		public static FormTask convert(Task entity) {
			return FormTask.builder()
					       .pkTaskGroup(entity.getTaskGroup().getPkTaskGroup())
					       .pkTask(entity.getPkTask())
					       .task(entity.getTask())
					       .isCompleted(entity.isCompleted())
					       .dueDate(entity.getDueDate())
					       .comments(entity.getComments())
						   .build();	
		}
	}	
	
}
