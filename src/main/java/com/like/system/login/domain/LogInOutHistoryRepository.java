package com.like.system.login.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogInOutHistoryRepository extends JpaRepository<LogInOutHistory, Long> {

}
