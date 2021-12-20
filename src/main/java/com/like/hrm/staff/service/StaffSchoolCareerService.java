package com.like.hrm.staff.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.schoolcareer.SchoolCareer;


@Transactional
@Service
public class StaffSchoolCareerService {

	private StaffRepository repository;
				
	public StaffSchoolCareerService(StaffRepository repository) {
		this.repository = repository;	
	}
	
	public SchoolCareer getSchoolCareer(String empId, Long id) {
		Staff emp = getEmployeeInfo(empId);
		
		return emp.getSchoolCareerList().get(id);
	}
	
	public void saveSchoolCareer(StaffDTO.FormEducation dto) {
		Staff emp = getEmployeeInfo(dto.getStaffId());
		
		SchoolCareer education = emp.getSchoolCareerList().get(dto.getEducationId());
		
		if (education == null) {
			education = dto.newEntity(emp);
		} else {
			dto.modifyEnity(education);
		}
		
		emp.getSchoolCareerList().add(education);
		
		repository.save(emp);
	}
	
	private Staff getEmployeeInfo(String empId) {
		return repository.findById(empId)
				 .orElseThrow(() -> new EntityNotFoundException(empId + " 사번이 존재하지 않습니다."));
	}
}
