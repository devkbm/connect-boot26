package com.like.cooperation.team.boundary;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.like.cooperation.team.domain.TeamMember;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public class TeamMemberDTO {

	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	@Builder
	public static class FormTeamMember implements Serializable {
				
		private static final long serialVersionUID = -8615708613747408595L;

		@NotEmpty(message = "팅 ID는 필수 입력 값입니다.")
		Long teamId;
		
		@NotEmpty(message = "유저 ID는 필수 입력 값입니다.")
		String userId;
		
		String authority;
		
		public static FormTeamMember convert(TeamMember entity) {
			if (entity == null) return null;
						
			return FormTeamMember.builder()
								 .teamId(entity.getTeam().getTeamId())
								 .userId(entity.getUser().getUserId())
								 .authority(entity.getAuthority())
								 .build();
		}
		
	}
}