package com.like.hrm.hrmtypecode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.hrmtypecode.boundary.HrmTypeDTO;
import com.like.hrm.hrmtypecode.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.hrmtypecode.domain.HrmType;
import com.like.hrm.hrmtypecode.domain.HrmTypeDetailCode;
import com.like.hrm.hrmtypecode.domain.HrmTypeQueryRepository;

@Service
@Transactional(readOnly = true)
public class HrmTypeQueryService {

	private HrmTypeQueryRepository repository;	
		
	public HrmTypeQueryService(HrmTypeQueryRepository repository) {		
		this.repository = repository;
	}
				
	public List<HrmType> getHrmTypeList(HrmTypeDTO.SearchHrmType condition) {
		return repository.getHrmTypeList(condition);
	}
	
	public List<HrmTypeDetailCode> getTypeDetailCodeList(HrmTypeDetailCodeDTO.SearchHrmTypeDetailCode condition) {
		return repository.getTypeDetailCodeList(condition);
	}	
	
	
	
}
