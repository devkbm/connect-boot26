package com.like.hrm.staff.service;

import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffName;
import com.like.hrm.staff.domain.model.StaffRepository;

@Service
@Transactional
public class StaffService {
	
	private StaffRepository repository;	
		
	public StaffService(StaffRepository repository) {
		this.repository = repository;
	}	
	
	public Staff getStaff(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public void saveStaff(Staff employee) {				
		repository.save(employee);
	}
	
	public void saveStaff(StaffDTO.FormStaff dto) {
		Staff staff = this.getStaff(dto.staffId());
		
		dto.modifyEntity(staff);
		
		repository.save(staff);
	}
	
	public void newStaff(StaffDTO.NewStaff dto) {		
		if (isExistStaff(dto.staffId())) throw new EntityExistsException("동일 직원번호가 존재합니다 : " + dto.staffId());
						
		Staff staff = Staff.of(dto.staffId()
							  ,new StaffName(dto.name(), dto.nameEng(), dto.nameEng())
							  ,dto.residentRegistrationNumber());
		
		repository.save(staff);
	}
	
	public void deleteStaff(String id) {		
		repository.deleteById(id);
	}
		
	private boolean isExistStaff(String id) {
		return repository.findById(id).isPresent();
	}
		
}
