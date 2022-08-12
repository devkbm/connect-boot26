package com.like.system.dept.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.dept.boundary.DeptDTO;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.DeptRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class DeptService {

	private DeptRepository deptRepository;
	
	public DeptService(DeptRepository deptRepository) {
		this.deptRepository = deptRepository;
	}

	public boolean isDept(String deptID) {
		return deptRepository.existsById(deptID);
	}
	
	public Dept getDept(String deptID) {
		return deptRepository.findById(deptID).orElse(null);
	}	
	
	public void createDept(Dept dept) {
		deptRepository.save(dept);
	}			
	
	public void saveDept(Dept dept) {				
		deptRepository.save(dept);
	}
	
	public void saveDept(DeptDTO.FormDept dto) {
		Dept dept = dto.deptId() == null ? null : deptRepository.findById(dto.deptId()).orElse(null);
		Dept parentDept = dto.parentDeptId() == null ? null : deptRepository.findById(dto.parentDeptId()).orElse(null); 			
		
		if (dept == null) {
			dept = dto.newDept(parentDept);
		} else {
			dto.modifyDept(dept, parentDept);
		}				
		log.info(dept.toString());
		
		deptRepository.save(dept);
	}
	
	public void deleteDept(String deptID) {
		deptRepository.deleteById(deptID);
	}
}
