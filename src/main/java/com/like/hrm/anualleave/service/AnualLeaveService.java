package com.like.hrm.anualleave.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.anualleave.boundary.AnualLeaveDTO;
import com.like.hrm.anualleave.domain.model.AnualLeave;
import com.like.hrm.anualleave.domain.model.AnualLeaveId;
import com.like.hrm.anualleave.domain.model.AnualLeaveRepository;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffRepository;

@Service
@Transactional
public class AnualLeaveService {
		
	private AnualLeaveRepository repository;
	private StaffRepository staffRepository;
	
	public AnualLeaveService(AnualLeaveRepository repository
							,StaffRepository staffRepository) {
		this.repository = repository;
		this.staffRepository = staffRepository;
	}
	
	public AnualLeave getAnualLeave(String staffId, Integer yyyy) {
		Staff staff = findStaff(staffId);
		return this.repository.findById(new AnualLeaveId(staff, yyyy)).orElse(null);		
	}
	
	public void saveAnualLeave(AnualLeaveDTO.SaveAnualLeave dto) {
		Staff staff = findStaff(dto.staffId());
		AnualLeave entity = this.getAnualLeave(dto.staffId(), dto.yyyy());
		
		if (entity == null) {
			entity = dto.newAnualLeave(staff);
		} else {
			dto.modifyEntity(entity);
		}
		
		this.repository.save(entity);
	}
	
	public void deleteAnualLeave(String staffId, Integer yyyy) {
		AnualLeave entity = this.getAnualLeave(staffId, yyyy);
		
		this.repository.delete(entity);
	}
	
	
	private Staff findStaff(String staffId) {
		return staffRepository.findById(staffId)
				 .orElseThrow(() -> new EntityNotFoundException(staffId + " 직원번호가 존재하지 않습니다."));
	}
	
}
