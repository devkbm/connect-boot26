package com.like.system.login.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.login.domain.LogInOutHistory;
import com.like.system.login.domain.LogInOutHistoryRepository;

@Transactional
@Service
public class LogInOutHistoryService {

	private LogInOutHistoryRepository repository;
	
	public LogInOutHistoryService(LogInOutHistoryRepository repository) {
		this.repository = repository;
	}
	
	public void saveLogInOutHistory(LogInOutHistory entity) {
		repository.save(entity);
	}
}
