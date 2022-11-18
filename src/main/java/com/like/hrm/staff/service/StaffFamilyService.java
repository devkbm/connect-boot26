package com.like.hrm.staff.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.FamilyDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.family.StaffFamily;

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
						
		return staff.getFamilyList().get(staff, seq);
	}
	
	public void saveFamily(FamilyDTO.Form dto) {
		Staff staff = findStaff(dto.staffId());		
		StaffFamily entity = staff.getFamilyList().get(staff, dto.seq());
		
		if (entity == null) {
			entity = dto.newEntity(staff);
		} else {
			dto.modifyEntity(entity);
		}
		
		staff.getFamilyList().add(entity);
		
		repository.save(staff);
	}
	
	public void deleteFamily(String staffId, Long seq) {
		Staff staff = findStaff(staffId);
		
		staff.getFamilyList().remove(staff, seq);
	}
	
	private Staff findStaff(String staffId) {
		return repository.findById(staffId)
						 .orElseThrow(() -> new EntityNotFoundException(staffId + " 직원번호가 존재하지 않습니다."));
	}
}
