package com.like.system.biztypecode.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BizTypeCodeRepository extends JpaRepository<BizTypeCode, String> {

}
