package com.like.cooperation.workschedule.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.like.cooperation.workschedule.boundary.WorkDTO;
import com.like.cooperation.workschedule.domain.WorkGroup;
import com.like.cooperation.workschedule.domain.WorkGroupMember;
import com.like.cooperation.workschedule.domain.WorkGroupMemberId;
import com.like.cooperation.workschedule.domain.WorkGroupMemberRepository;
import com.like.cooperation.workschedule.domain.WorkGroupRepository;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserRepository;

@Service
@Transactional
public class WorkGroupService {

	private WorkGroupRepository repository;
	private WorkGroupMemberRepository workGroupMemberRepository;
	private SystemUserRepository userRepository;
	
	public WorkGroupService(WorkGroupRepository repository
						   ,WorkGroupMemberRepository workGroupMemberRepository	
						   ,SystemUserRepository userRepository) {		
		this.repository = repository;
		this.workGroupMemberRepository = workGroupMemberRepository;
		this.userRepository = userRepository;
	}						
	
	/**
	 * 업무그룹를 조회한다.
	 * @param id
	 * @return
	 */
	public WorkGroup getWorkGroup(Long id) {
		return repository.findById(id).orElse(null);
	}			
	
	public void saveWorkGroup(WorkDTO.Form dto) {
		WorkGroup entity = null;
		
		if (dto.workGroupId() != null) {
			entity = repository.findById(dto.workGroupId()).orElse(null);
		}
		
		if (entity == null) {
			entity = dto.newWorkGroup();
		} else {
			dto.modifyWorkGroup(entity);
		}
		
		List<String> dtoMemberList = dto.memberList();
		entity.clearWorkGroupMember();
		
		if (dtoMemberList != null) {
			List<SystemUser> userList = userRepository.findAllById(dtoMemberList);
			
			for ( SystemUser user: userList ) {
				WorkGroupMember member = new WorkGroupMember(entity, user);				
				entity.addWorkGroupMember(member);
			}
			//workGroupService.saveWorkGroupMember(entity, user);
		}	
				
		repository.save(entity);
	}
	
	public void deleteWorkGroup(Long id) {
		repository.deleteById(id);
	}
	
	public WorkGroupMember getWorkGroupMember(WorkGroupMemberId id) {
		return workGroupMemberRepository.findById(id).orElse(null);
	}
	
	public void saveWorkGroupMember(WorkGroup workGroup, SystemUser user) {
		workGroup.addWorkGroupMember(new WorkGroupMember(workGroup, user));
	}
	
	public void saveWorkGroupMember(WorkGroup workGroup, List<SystemUser> userList) {		
		for (SystemUser user: userList) {
			workGroup.addWorkGroupMember(new WorkGroupMember(workGroup, user));
		}
		
		repository.save(workGroup);
	}

	public void deleteWorkGroupMember(WorkGroupMember workGroupMember) {
		workGroupMemberRepository.delete(workGroupMember);
	}
		

	
}
