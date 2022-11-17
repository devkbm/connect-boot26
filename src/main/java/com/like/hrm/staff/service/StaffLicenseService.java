package com.like.hrm.staff.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.StaffDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.license.StaffLicense;
import com.like.hrm.staff.domain.model.license.StaffLicenseId;

@Transactional
@Service
public class StaffLicenseService {

	private StaffRepository repository;
	
	public StaffLicenseService(StaffRepository repository) {
		this.repository = repository;	
	}
	
	public List<StaffLicense> getLicenseList(String staffId) {
		Staff staff = findStaff(staffId);
		
		return staff.getLicenseList().getStream().toList();
	}
	
	
	public StaffLicense getLicense(String staffId, Long seq) {
		Staff staff = findStaff(staffId);
						
		return staff.getLicenseList().get(new StaffLicenseId(staff, seq));
	}
	
	public void saveLicense(StaffDTO.FormLicense dto) {
		Staff staff = findStaff(dto.staffId());
		
		StaffLicense license = staff.getLicenseList().get(new StaffLicenseId(staff, dto.seq()));
		
		if (license == null) {
			license = dto.newEntity(staff);
		} else {
			dto.modifyEntity(license);
		}
		
		staff.getLicenseList().add(license);
		
		repository.save(staff);
	}
	
	public void deleteLicense(String staffId, Long seq) {
		Staff staff = findStaff(staffId);
		
		staff.getLicenseList().remove(new StaffLicenseId(staff, seq));
	}
	
	private Staff findStaff(String staffId) {
		return repository.findById(staffId)
				 .orElseThrow(() -> new EntityNotFoundException(staffId + " 직원번호가 존재하지 않습니다."));
	}
}
