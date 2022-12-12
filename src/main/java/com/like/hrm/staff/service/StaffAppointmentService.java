package com.like.hrm.staff.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.AppointmentRecordDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecordList;

@Transactional
@Service
public class StaffAppointmentService {

	private StaffRepository repository;
	
	public StaffAppointmentService(StaffRepository repository) {
		this.repository = repository;		
	}
	
	public AppointmentRecordList getAppointmentRecord(String staffId) {
		return findStaff(staffId).getAppointmentRecordList();
	}
	
	public AppointmentRecord getAppointmentRecord(String staffId, Long seq) {
		Staff staff = findStaff(staffId);
		
		return staff.getAppointmentRecordList().get(staff, seq);	
	}
	
	public void saveAppointmentRecord(AppointmentRecordDTO.FormStaffAppointmentRecord dto) {
		Staff staff = findStaff(dto.staffId());		
		AppointmentRecord entity = staff.getAppointmentRecordList().get(staff, dto.seq());
		
		if (entity == null) {
			entity = dto.newEntity(staff);
		} else {
			dto.modifyEntity(entity);
		}
		
		staff.getAppointmentRecordList().add(entity);		
		staff.applyAppointmentRecord(entity);		 
		
		repository.save(staff);
	}	
	
	public void delete(String staffId, Long seq) {
		Staff staff = findStaff(staffId);
		staff.getAppointmentRecordList().remove(staff, seq);
		
		repository.save(staff);
	}
	
	public void applyAppointmentRecord(String staffId, Long seq) {
		Staff staff = findStaff(staffId);
		AppointmentRecord entity = staff.getAppointmentRecordList().get(staff, seq);
		
		staff.applyAppointmentRecord(entity);					
	}
	
	private Staff findStaff(String staffId) {
		return repository.findById(staffId)
						 .orElseThrow(() -> new EntityNotFoundException(staffId + " 직원번호가 존재하지 않습니다."));
	}
		
}
