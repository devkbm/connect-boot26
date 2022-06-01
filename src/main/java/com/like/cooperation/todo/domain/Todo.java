package com.like.cooperation.todo.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.system.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true, exclude = {"todoGroup"})
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name = "grtodo")
@EntityListeners(AuditingEntityListener.class)
public class Todo extends AuditEntity {		

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_todo")
	Long pkTodo;	

	@Column(name="todo")
	String todo;		
	
	@Column(name="completed")
	boolean isCompleted;
	
	@Column(name="due_dt")
    LocalDate dueDate;
	
	@Column(name="comments")
	String comments;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_todo_group", nullable=false)
	TodoGroup todoGroup;	
	
	@Builder
	public Todo(TodoGroup todoGroup, String todo, LocalDate dueDate, String comments) {
		this.todoGroup = todoGroup;
		this.todo = todo;
		this.dueDate = dueDate;
		this.comments = comments;
	}
	
	public void modify(String todo
					  ,boolean isCompleted
					  ,LocalDate dueDate
					  ,String comments) {
		this.todo = todo;
		this.isCompleted = isCompleted;
		this.dueDate = dueDate;
		this.comments = comments;		
	}
	
}
