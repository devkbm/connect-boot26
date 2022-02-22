package com.like.hrm.staff.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.family.Family;

@Transactional
@Service
public class StaffFamilyService {

	private StaffRepository repository;
	
	public StaffFamilyService(StaffRepository repository) {
		this.repository = repository;
	}
	
	public Family getFamily(String empId, Long id) {
		Staff emp = getEmployeeInfo(empId);
						
		return emp.getFamilyList().get(id);
	}
	
	public void saveFamily(StaffDTO.FormFamily dto) {
		Staff emp = getEmployeeInfo(dto.staffId());
		
		Family entity = emp.getFamilyList().get(dto.id());
		
		if (entity == null) {
			entity = dto.newEntity(emp);
		} else {
			dto.modifyEntity(entity);
		}
		
		emp.getFamilyList().add(entity);
		
		repository.save(emp);
	}	
	
	private Staff getEmployeeInfo(String empId) {
		return repository.findById(empId)
						 .orElseThrow(() -> new EntityNotFoundException(empId + " 사번이 존재하지 않습니다."));
	}
}
