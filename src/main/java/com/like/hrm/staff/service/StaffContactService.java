package com.like.hrm.staff.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffContact;
import com.like.hrm.staff.domain.model.StaffRepository;

@Service
@Transactional
public class StaffContactService {

	private StaffRepository repository;	
	
	public StaffContactService(StaffRepository repository) {
		this.repository = repository;
	}
	
	public StaffContact get(String staffId) {
		Staff staff = repository.findById(staffId).orElse(null);
		
		return staff.getContact();
	}
	
	public void save() {
		
	}
}
