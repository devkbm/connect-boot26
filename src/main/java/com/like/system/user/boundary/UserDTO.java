package com.like.system.user.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.like.system.core.jpa.validation.UserIdExists;
import com.like.system.dept.domain.Dept;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.user.domain.Authority;
import com.like.system.user.domain.QSystemUser;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.vo.AccountSpec;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

public class UserDTO {
	
	public record Search(
			String userId,
			String name,
			String deptCode
			) {
		
		private static final QSystemUser qType = QSystemUser.systemUser;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(likeUserId(this.userId))
			 	   .and(likeUserName(this.name))
			 	   .and(equalDeptCode(this.deptCode));						
			
			return builder;
		}
		
		private BooleanExpression likeUserId(String userId) {
			return hasText(userId) ? qType.id.like("%"+userId+"%") : null;					
		}
		
		private BooleanExpression likeUserName(String name) {
			return hasText(name) ? qType.name.like("%"+name+"%") : null;					
		}
		
		private BooleanExpression equalDeptCode(String deptCode) {
			return hasText(deptCode) ? qType.dept.deptCode.eq(deptCode) : null;					
		}
	}		
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class FormSystemUser {
		
		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@NotBlank(message="아이디를 입력해주세요")
		@Size(min=1, max=20, message="1자 이상 20자 이하의 아이디만 사용 가능합니다")
		@Pattern(regexp="^[A-Za-z0-9+]*$",message="영문,숫자로 이루어진 아이디만 사용 가능합니다")
		@UserIdExists(message="이미 가입한 아이디입니다")
		String userId;
		
		String staffNo;
			
		String name;			
		
		String organizationCode;
		
		String deptCode;
		
		String mobileNum;
		
		String email;
		
		String imageBase64;
				
		Boolean accountNonExpired;
			
		Boolean accountNonLocked;
			
		Boolean credentialsNonExpired;
			
		Boolean enabled;
					
		@Singular(value = "authorityList")
		Set<String> authorityList;

		@Singular(value = "menuGroupList")
		Set<String> menuGroupList; 
		
		public SystemUser newUser(Dept dept, Set<Authority> authorityList, Set<MenuGroup> menuGroupList) {
			return SystemUser.builder()
					   .id(this.userId)
					   .staffNo(this.staffNo)
					   .name(this.name)		
					   .organizationCode(this.organizationCode)
					   .dept(dept)				
					   .mobileNum(this.mobileNum)
					   .email(this.email)					  
					   .accountSpec(new AccountSpec(true, true, true, true))
					   .authorities(authorityList)
					   .menuGroupList(menuGroupList)					
					   .build();
			
		}
		
		public void modifyUser(SystemUser user, Dept dept, Set<Authority> authorityList, Set<MenuGroup> menuGroupList) {
			user.modifyEntity(staffNo
							 ,name		
							 ,organizationCode 
							 ,mobileNum
							 ,email							  
							 ,dept
							 ,authorityList
							 ,menuGroupList);	
		}
		
		public static UserDTO.FormSystemUser convertDTO(SystemUser entity) {					
			
			if (entity == null) return null;
			
			Optional<Dept> dept = Optional.ofNullable(entity.getDept());
			
			FormSystemUser dto = FormSystemUser.builder()								
											   .userId(entity.getId())
											   .staffNo(entity.getStaffNo())
											   .name(entity.getName())	
											   .organizationCode(entity.getOrganizationCode())
											   .deptCode(dept.map(Dept::getDeptCode).orElse(null))
											   .mobileNum(entity.getMobileNum())
											   .email(entity.getEmail())
											   .imageBase64(entity.getImage())
											   .enabled(entity.isEnabled())	
											   .accountNonExpired(entity.isAccountNonExpired())
											   .accountNonLocked(entity.isAccountNonLocked())
											   .credentialsNonExpired(entity.isCredentialsNonExpired())
											   .authorityList(entity.getAuthoritiesList()
																	.stream()
																	.map(auth -> auth.getAuthority())
																	.collect(Collectors.toList()))
											   .menuGroupList(entity.getMenuGroupList()
																	.stream()
																	.map(menuGroup -> menuGroup.getId())
																	.collect(Collectors.toList()))
											   .build();
			
			return dto;
		}
								
	}

	

}
