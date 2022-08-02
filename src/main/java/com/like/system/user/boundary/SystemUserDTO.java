package com.like.system.user.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.like.system.dept.domain.Dept;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.user.domain.Authority;
import com.like.system.user.domain.QSystemUser;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.vo.AccountSpec;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class SystemUserDTO {
	
	public record Search(
			@NotBlank(message="조직 코드를 선택해주세요.")
			String organizationCode,
			String staffNo,			
			String name,
			String deptCode
			) {
		
		private static final QSystemUser qType = QSystemUser.systemUser;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(eqOrganizationCode(this.organizationCode))
				   .and(likeStaffNo(this.staffNo))
				   .and(likeUserName(this.name))
			 	   .and(equalDeptCode(this.deptCode));						
			
			return builder;
		}
		
		private BooleanExpression eqOrganizationCode(String organizationCode) {
			return qType.organizationCode.eq(organizationCode);
		}
				
		private BooleanExpression likeStaffNo(String staffNo) {
			return hasText(staffNo) ? qType.staffNo.like("%"+staffNo+"%") : null;					
		}
		
		private BooleanExpression likeUserName(String name) {
			return hasText(name) ? qType.name.like("%"+name+"%") : null;					
		}
		
		private BooleanExpression equalDeptCode(String deptCode) {
			return hasText(deptCode) ? qType.dept.deptCode.eq(deptCode) : null;					
		}
	}		
	
	@Builder
	public static record FormSystemUser(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			String appUrl,
			String userId,
			@NotBlank(message="조직코드를 선택해 주세요.")
			String organizationCode,
			@NotBlank(message="직원번호를 입력해 주세요.")
			String staffNo,
			String name,
			String deptCode,
			String mobileNum,
			String email,
			String imageBase64,
			Boolean accountNonExpired,
			Boolean accountNonLocked,
			Boolean credentialsNonExpired,
			Boolean enabled,
			List<String> authorityList,
			List<String> menuGroupList
			) {
		
		public SystemUser newUser(Dept dept, Set<Authority> authorityList, Set<MenuGroup> menuGroupList) {
			return SystemUser.builder()					  					  
					   .name(this.name)		
					   .organizationCode(this.organizationCode)
					   .staffNo(this.staffNo)
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
		
		public static SystemUserDTO.FormSystemUser convertDTO(SystemUser entity) {					
			
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
																	.toList())
											   .menuGroupList(entity.getMenuGroupList()
																	.stream()
																	.map(menuGroup -> menuGroup.getId())
																	.toList())
											   .build();
			
			return dto;
		}
	}
	
	
	

}
