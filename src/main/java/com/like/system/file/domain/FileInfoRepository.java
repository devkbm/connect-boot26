package com.like.system.file.domain;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FileInfoRepository extends JpaRepository<FileInfo, UUID>, QuerydslPredicateExecutor<FileInfo> {
	
}
