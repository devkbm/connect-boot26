package com.like;

import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootApplication
public class ConnectBoot26Application {

	public static void main(String[] args) {
		SpringApplication.run(ConnectBoot26Application.class, args);
	}

	@PersistenceContext
	EntityManager em;

	@Bean
	public JPAQueryFactory queryFactory() {
		return new JPAQueryFactory(em);
	}
	
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
