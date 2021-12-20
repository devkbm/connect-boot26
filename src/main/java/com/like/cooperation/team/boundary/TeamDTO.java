package com.like.cooperation.team.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.like.cooperation.team.domain.QTeam;
import com.like.cooperation.team.domain.Team;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TeamDTO {
	
	@Data
	public static class SearchCondition implements Serializable {
		
		private static final long serialVersionUID = -4777670465777456711L;

		private final QTeam qType = QTeam.team;			
				
		Long teamId;
		
		String teamName;						
		
		public BooleanBuilder getCondition() {									
			return new BooleanBuilder()
					.and(eqTeamId(teamId))
					.and(likeTeamName(teamName));
		}
		
		private BooleanExpression eqTeamId(Long teamId) {
			if (teamId == null) return null;
							
			return qType.teamId.eq(teamId);		
		}
		
		private BooleanExpression likeTeamName(String teamName) {
			if (!StringUtils.hasText(teamName)) return null;
							
			return qType.teamName.like("%" + teamName + "%");		
		}
		
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class FormTeam implements Serializable {
						
		private static final long serialVersionUID = -3606614600656958895L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		Long teamId;

		String teamName;
		
		List<String> memberList;
		
		public Team newEntity() {						
			return new Team(teamName);
		}
		
		public Team modify(Team entity) {
			entity.changeTeamName(teamName);
			
			return entity;
		}
		
		public static TeamDTO.FormTeam convert(Team entity) {					
			
			if (entity == null) return null;
			
			TeamDTO.FormTeam dto = TeamDTO.FormTeam.builder()
									.createdDt(entity.getCreatedDt())
									.createdBy(entity.getCreatedBy())
									.modifiedDt(entity.getModifiedDt())
									.modifiedBy(entity.getModifiedBy())
									.teamId(entity.getTeamId())
									.teamName(entity.getTeamName())
									.memberList(entity.getMemberList().stream()
																	  .map(r -> r.getUserId())
																	  .collect(Collectors.toList()))	
									.build();		
			return dto;
		}
	}
	
	
	
		
}
