package com.like.system.file.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FileInfoRepository extends JpaRepository<FileInfo, String>, QuerydslPredicateExecutor<FileInfo> {
	
}
