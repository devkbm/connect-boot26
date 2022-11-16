package com.like.hrm.staff.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.family.StaffFamily;
import com.like.hrm.staff.domain.model.family.StaffFamilyId;

@Transactional
@Service
public class StaffFamilyService {

	private StaffRepository repository;
	
	public StaffFamilyService(StaffRepository repository) {
		this.repository = repository;
	}
	
	public List<StaffFamily> getFamilyList(String staffId) {
		Staff staff = findStaff(staffId);
		
		return staff.getFamilyList().getStream().toList();
	}
	
	public StaffFamily getFamily(String staffId, Long seq) {
		Staff staff = findStaff(staffId);
						
		return staff.getFamilyList().get(new StaffFamilyId(staff, seq));
	}
	
	public void saveFamily(StaffDTO.FormFamily dto) {
		Staff staff = findStaff(dto.staffId());
		
		StaffFamily entity = staff.getFamilyList().get(new StaffFamilyId(staff, dto.seq()));
		
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
