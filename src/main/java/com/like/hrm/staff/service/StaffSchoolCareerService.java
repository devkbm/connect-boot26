package com.like.hrm.staff.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.SchoolCareerDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.schoolcareer.StaffSchoolCareer;


@Transactional
@Service
public class StaffSchoolCareerService {

	private StaffRepository repository;
				
	public StaffSchoolCareerService(StaffRepository repository) {
		this.repository = repository;	
	}
	
	public List<StaffSchoolCareer> getSchoolCareerList(String staffId) {		
		return findStaff(staffId).getSchoolCareerList().getStream().toList(); 
	}
	
	public StaffSchoolCareer getSchoolCareer(String staffId, Long seq) {
		Staff staff = findStaff(staffId);
		
		return staff.getSchoolCareerList().get(staff, seq);
	}
	
	public void saveSchoolCareer(SchoolCareerDTO.Form dto) {
		Staff staff = findStaff(dto.staffId());	
		StaffSchoolCareer education = staff.getSchoolCareerList().get(staff, dto.seq());
		
		if (education == null) {
			education = dto.newEntity(staff);
		} else {
			dto.modifyEnity(education);
		}
		
		staff.getSchoolCareerList().add(education);
		
		repository.save(staff);
	}
	
	public void deleteSchoolCareer(String staffId, Long seq) {
		Staff staff = findStaff(staffId);
		staff.getSchoolCareerList().remove(staff, seq);
	}
	
	private Staff findStaff(String staffId) {
		return repository.findById(staffId)
				 .orElseThrow(() -> new EntityNotFoundException(staffId + " 직원번호가 존재하지 않습니다."));
	}
}
