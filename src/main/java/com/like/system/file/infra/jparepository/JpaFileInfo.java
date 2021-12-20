package com.like.system.file.infra.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.system.file.domain.FileInfo;

@Repository
public interface JpaFileInfo extends JpaRepository<FileInfo, String> {
	List<FileInfo> findByPgmId(String pgmId);
}
