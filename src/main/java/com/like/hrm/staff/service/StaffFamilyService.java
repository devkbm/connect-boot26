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
	
	public Family getFamily(String staffId, Long id) {
		Staff emp = findStaff(staffId);
						
		return emp.getFamilyList().get(id);
	}
	
	public void saveFamily(StaffDTO.FormFamily dto) {
		Staff staff = findStaff(dto.staffId());
		
		Family entity = staff.getFamilyList().get(dto.id());
		
		if (entity == null) {
			entity = dto.newEntity(staff);
		} else {
			dto.modifyEntity(entity);
		}
		
		staff.getFamilyList().add(entity);
		
		repository.save(staff);
	}	
	
	private Staff findStaff(String staffId) {
		return repository.findById(staffId)
						 .orElseThrow(() -> new EntityNotFoundException(staffId + " 직원번호가 존재하지 않습니다."));
	}
}
