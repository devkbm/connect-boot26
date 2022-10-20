package com.like.hrm.hrmcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.hrmcode.boundary.HrmRelationCodeDTO;
import com.like.hrm.hrmcode.domain.HrmRelationCodeQueryRepository;

@Service
@Transactional(readOnly = true)
public class HrmRelationCodeQueryService {

	private HrmRelationCodeQueryRepository repository;
	
	public HrmRelationCodeQueryService(HrmRelationCodeQueryRepository repository) {
		this.repository = repository;
	}
	
	public List<?> getHrmRelationCodeList(HrmRelationCodeDTO.SearchHrmRelationCode condition) {
		return null;
		//return repository.getRelationCodeList(condition);
	}
}
