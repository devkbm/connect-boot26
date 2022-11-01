package com.like.hrm.staff.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffContact;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.system.core.jpa.vo.Address;
import com.like.system.core.jpa.vo.PhoneNumber;

@Service
@Transactional
public class StaffContactService {

	private StaffRepository repository;	
	
	public StaffContactService(StaffRepository repository) {
		this.repository = repository;
	}
	
	public StaffDTO.FormContact get(String staffId) {
		Staff staff = repository.findById(staffId).orElse(null);
		
		return StaffDTO.FormContact.convert(staff);
	}
	
	public void save(StaffDTO.FormContact dto) {
		Staff staff = repository.findById(dto.staffId()).orElseThrow(() -> new IllegalArgumentException("직원정보가 존재하지 않습니다."));
						
		staff.changeContact(new StaffContact(new Address(dto.homeAddressType(), dto.homePostNumber(), dto.homeMainAddress(), dto.homeSubAddress())
						   ,new PhoneNumber(dto.extensionNumber())
						   ,new PhoneNumber(dto.mobileNumber())));
		
		this.repository.save(staff);
	}
	
	
}
