package com.like.hrm.staff.service;

import java.time.LocalDate;

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
		return getStaffInfo(staffId).getAppointmentRecordList();
	}
	
	public AppointmentRecord getAppointmentRecord(String staffId, Long id) {
		Staff staff = getStaffInfo(staffId);
		
		return staff.getAppointmentRecordList().get(id);	
	}
	
	public void saveAppointmentRecord(AppointmentRecordDTO.FormStaffAppointmentRecord dto) {
		Staff staff = getStaffInfo(dto.staffId());
		
		AppointmentRecord entity = staff.getAppointmentRecordList().get(dto.id());
		
		if (entity == null) {
			entity = dto.newEntity(staff);
		} else {
			dto.modifyEntity(entity);
		}
		
		staff.getAppointmentRecordList().add(entity);
		
		LocalDate today = LocalDate.now();
		
		if (today.isAfter(dto.appointmentDate())) {			
			staff.applyAppointmentRecord(entity);			
		} 
		
		repository.save(staff);
	}	
	
	public void applyAppointmentRecord(String staffId, Long appointmentRecordId) {
		Staff staff = getStaffInfo(staffId);
		AppointmentRecord entity = staff.getAppointmentRecordList().get(appointmentRecordId);
		staff.applyAppointmentRecord(entity);					
	}
	
	private Staff getStaffInfo(String staffId) {
		return repository.findById(staffId)
						 .orElseThrow(() -> new EntityNotFoundException(staffId + " 사번이 존재하지 않습니다."));
	}
}
