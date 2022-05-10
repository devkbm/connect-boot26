package com.like.cooperation.todo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class TodoRepositoryTest {

	@Autowired
	private TodoGroupRepository repository;
	
	@DisplayName("기본 할일 목록 생성")
	@Test
	void createDefaultTodoGroup() {
		//Given
		TodoGroup group = new TodoGroup();
		
		//When		
		repository.save(group);
		
		//Then
		assertThat(group.getTodoGroupName()).isEqualTo(TodoGroup.DEFAULT_GROUP_NAME);
	}
	
	@DisplayName("할일 목록 생성")
	@Test
	void createTodoGroup() {
		//Given
		TodoGroup group = new TodoGroup("프로그램개발");
		
		//When
		repository.save(group);
		
		//Then
		assertThat(group.getTodoGroupName()).isEqualTo("프로그램개발");
	}
	
	@DisplayName("할일 추가")
	@Test
	void addTodo() {
		//Given
		TodoGroup group = new TodoGroup();
		
		//When 
		group.addTodo(new Todo(group, "할일1", LocalDate.now(), "비고1"));
		group.addTodo(new Todo(group, "할일2", LocalDate.now(), "비고2"));
		repository.save(group);
		
		//Then
		assertThat(group.getTodoGroupName()).isEqualTo(TodoGroup.DEFAULT_GROUP_NAME);
		assertThat(group.getTodoList().size()).isEqualTo(2);
		
		assertThat(group.getTodoList().get(0).getTodoGroup()).isEqualTo(group);
		assertThat(group.getTodoList().get(0).getTodo()).isEqualTo("할일1");
		assertThat(group.getTodoList().get(0).isCompleted()).isEqualTo(false);
		assertThat(group.getTodoList().get(0).getDueDate()).isEqualTo(LocalDate.now());
		assertThat(group.getTodoList().get(0).getComments()).isEqualTo("비고1");
		
		assertThat(group.getTodoList().get(1).getTodoGroup()).isEqualTo(group);
		assertThat(group.getTodoList().get(1).getTodo()).isEqualTo("할일2");
		assertThat(group.getTodoList().get(1).isCompleted()).isEqualTo(false);
		assertThat(group.getTodoList().get(1).getDueDate()).isEqualTo(LocalDate.now());
		assertThat(group.getTodoList().get(1).getComments()).isEqualTo("비고2");		
	}
	
	@DisplayName("할일 수정")
	@Test
	void modifyTodo() {
		//Given
		TodoGroup group = new TodoGroup();
		Todo todo = new Todo(group, "할일1", LocalDate.now(), "비고1");
		group.addTodo(todo);		
		
		//When 
		todo.modify("할일2", true, LocalDate.of(2022, 1, 1), "비고2");	
		repository.save(group);
		
		log.info(group.toString());
		//Then
		assertThat(group.getTodoGroupName()).isEqualTo(TodoGroup.DEFAULT_GROUP_NAME);
		assertThat(group.getTodoList().size()).isEqualTo(1);
		
		assertThat(group.getTodoList().get(0).getTodoGroup()).isEqualTo(group);		
		assertThat(group.getTodoList().get(0).getTodo()).isEqualTo("할일2");
		assertThat(group.getTodoList().get(0).isCompleted()).isEqualTo(true);
		assertThat(group.getTodoList().get(0).getDueDate()).isEqualTo(LocalDate.of(2022, 1, 1));
		assertThat(group.getTodoList().get(0).getComments()).isEqualTo("비고2");					
	}
	
	@DisplayName("할일 삭제")
	@Test
	void deleteTodo() {
		//Given
		TodoGroup group = new TodoGroup();
		group.addTodo(new Todo(group, "할일1", LocalDate.now(), "비고1"));
		group.addTodo(new Todo(group, "할일2", LocalDate.now(), "비고2"));
		repository.saveAndFlush(group);
		
		//When 
		group.removeTodo(group.getTodoList().get(1).getPkTodo());
		repository.saveAndFlush(group);
		//Then
		assertThat(group.getTodoGroupName()).isEqualTo(TodoGroup.DEFAULT_GROUP_NAME);
		assertThat(group.getTodoList().size()).isEqualTo(1);
		
		assertThat(group.getTodoList().get(0).getTodoGroup()).isEqualTo(group);
		assertThat(group.getTodoList().get(0).getTodo()).isEqualTo("할일1");
		assertThat(group.getTodoList().get(0).isCompleted()).isEqualTo(false);
		assertThat(group.getTodoList().get(0).getDueDate()).isEqualTo(LocalDate.now());
		assertThat(group.getTodoList().get(0).getComments()).isEqualTo("비고1");				
	}
		
}
