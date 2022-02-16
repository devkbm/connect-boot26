package com.like.cooperation.team.boundary;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TeamMemberDTO {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class FormTeamMember implements Serializable {
		private String userId;
		
		
		
	}
}
