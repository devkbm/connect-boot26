package com.like.hrm.hrmcode.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrmCodeRepository extends JpaRepository<HrmCode, HrmCodeId> {

}
