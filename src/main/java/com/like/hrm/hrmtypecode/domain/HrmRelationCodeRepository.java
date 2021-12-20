package com.like.hrm.hrmtypecode.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrmRelationCodeRepository extends JpaRepository<HrmRelationCode, Long> {

}
