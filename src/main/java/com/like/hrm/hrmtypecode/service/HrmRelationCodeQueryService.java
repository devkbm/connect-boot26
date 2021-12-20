package com.like.hrm.hrmtypecode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.hrmtypecode.boundary.HrmRelationCodeDTO;
import com.like.hrm.hrmtypecode.domain.HrmRelationCodeQueryRepository;

@Service
@Transactional(readOnly = true)
public class HrmRelationCodeQueryService {

	private HrmRelationCodeQueryRepository repository;
	
	public HrmRelationCodeQueryService(HrmRelationCodeQueryRepository repository) {
		this.repository = repository;
	}
	
	public List<?> getHrmRelationCodeList(HrmRelationCodeDTO.SearchHrmRelationCode condition) {
		return repository.getRelationCodeList(condition);
	}
}
