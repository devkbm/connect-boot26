package com.like.cooperation.todo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.domain.AuditEntity;

import lombok.Getter;
import lombok.ToString;

@ToString(callSuper=true)
@Getter
@Entity
@Table(name = "grtodogroup")
@EntityListeners(AuditingEntityListener.class)
public class TodoGroup extends AuditEntity {		

	static String DEFAULT_GROUP_NAME = "기본일정";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_todo_group")
	Long pkTodoGroup;	
	
	@Column(name="task_group_name")
	String todoGroupName;		
	
	@OneToMany(mappedBy = "todoGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	List<Todo> todoList = new ArrayList<>();	
	
	public TodoGroup() {
		this.todoGroupName = DEFAULT_GROUP_NAME;
	}
	
	public TodoGroup(String todoGroupName) {
		this.todoGroupName = todoGroupName;
	}
	
	public void modify(String todoGroupName) {
		this.todoGroupName = todoGroupName;		
	}
	
	public Todo getTodo(Long id) {
		return this.todoList.stream().filter(e -> e.pkTodo.equals(id)).findFirst().orElse(null);
	}
	
	public void addTodo(Todo todo) {
		if (this.todoList == null) this.todoList = new ArrayList<>();
		
		this.todoList.add(todo);
	}
	
	public void removeTodo(Long id) {
		this.todoList.removeIf(e -> e.pkTodo.equals(id));		
	}
	
}
