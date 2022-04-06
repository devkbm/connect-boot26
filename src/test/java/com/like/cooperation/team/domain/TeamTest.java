package com.like.cooperation.team.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.like.system.user.domain.SystemUser;

import lombok.extern.slf4j.Slf4j;

public class TeamTest {

	@DisplayName("팀 생성")
	@Test
	void createEntity() {
		// Given 			
		// When
		Team team = new Team("테스트 팀");				
		// Then
		assertThat(team.getTeamName()).isEqualTo("테스트 팀");							
	}
	
	@DisplayName("팀 수정")
	@Test
	void modifyEntity() {
		// Given 			
		Team team = new Team("테스트 팀");
		// When
		team.modify("테스트 팀수정");				
		
		// Then
		assertThat(team.getTeamName()).isEqualTo("테스트 팀수정");							
	}
	
	@DisplayName("팀 가입")
	@Test
	void TeamJoin() {
		// Given 			
		Team team = new Team("테스트 팀");
		SystemUser user = new SystemUser("test", "테스트유저", null, null, null, null, null, null, null);
		SystemUser user2 = new SystemUser("test2", "테스트유저2", null, null, null, null, null, null, null);		
		// When
		team.addMember(user);			
		team.addMember(user2);
		// Then
		assertThat(team.getMembers().size()).isEqualTo(2);
		assertThat(team.getMembers().get(0).getUser().getUserId()).isEqualTo("test");
		assertThat(team.getMembers().get(1).getUser().getUserId()).isEqualTo("test2");
	}
}
