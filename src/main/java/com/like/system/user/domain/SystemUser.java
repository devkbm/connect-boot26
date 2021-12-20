package com.like.system.user.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.like.system.core.domain.AuditEntity;
import com.like.system.dept.domain.Dept;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.user.domain.vo.AccountSpec;
import com.like.system.user.domain.vo.UserPassword;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true, exclude = {"menuGroupList"})
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "comuser")
public class SystemUser extends AuditEntity implements UserDetails {	
	
	private static final long serialVersionUID = -4328973281359262612L;

	@Id	
	@Column(name="user_id")
	String userId;
	
	@Column(name="user_name")
	String name;
			
	@Embedded
	UserPassword password;
		
	@Embedded
	AccountSpec accountSpec;
	
	@Column(name="mobile_num")
	String mobileNum;
	
	@Column(name="email")
	String email;
				
	@Column(name="fk_file")
	String image;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "dept_cd", nullable = true)
	Dept dept;
		
	@Setter
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="comuserauthority",
    		joinColumns= @JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="authority_name"))	
	List<Authority> authorities = new ArrayList<>();
			
	@Setter
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="comusermenugroup",
    		joinColumns= @JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="menu_group_code"))	
	List<MenuGroup> menuGroupList = new ArrayList<>();		
		
	@Builder
	public SystemUser(String userId
					 ,String name
					 ,UserPassword password
					 ,Dept dept
					 ,String mobileNum
					 ,String email
					 ,AccountSpec accountSpec
					 ,List<Authority> authorities
					 ,List<MenuGroup> menuGroupList) {		
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.dept = dept;
		this.mobileNum = mobileNum;
		this.email = email;
		this.accountSpec = accountSpec;		
		this.authorities = authorities;
		this.menuGroupList = menuGroupList;
		
		this.initPassword();
	}	
	
	public void modifyEntity(String name														
							,String mobileNum
							,String email							 
							,Dept dept
							,List<Authority> authorities
							,List<MenuGroup> menuGroupList) {
		this.name = name;				
		this.mobileNum = mobileNum;
		this.email = email;		
		this.dept = dept;
		this.authorities = authorities;
		this.menuGroupList = menuGroupList;
	}
	
	@Override	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public List<Authority> getAuthoritiesList() {
		return this.authorities;
	}
			
	@Override	
	public String getUsername() {		
		return userId;
	}

	@Override		
	public String getPassword() {
		return password.getPassword();
	}		

	@Override
	public boolean isAccountNonExpired() {
		return accountSpec.getIsAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountSpec.getIsAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return accountSpec.getIsCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return accountSpec.getIsEnabled();
	}			
	
	public boolean isVaild(String password) {
		return this.password.matchPassword(password);
	}		
		
	public void addAuthoritiy(Authority authority) {
		if (this.authorities == null) {
			this.authorities = new ArrayList<>();
		}
		
		this.authorities.add(authority);
	}								
	
	public void changePassword(String password) {
		this.password = new UserPassword(password);
	}
	
	/**
	 * 비밀번호를 초기화한다. 
	 * 초기화 비밀번호 : 12345678
	 */
	public void initPassword() {
		if (this.password == null) {
			this.password = new UserPassword();
		} else {
			this.password.init();
		}							
	}
	
	public void changeImage(String imageFileInfo) {
		this.image = imageFileInfo;
	}	
	
}
