package com.like.system.user.domain;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

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
import org.springframework.web.multipart.MultipartFile;

import com.like.system.core.jpa.domain.AbstractAuditEntity;
import com.like.system.dept.domain.Dept;
import com.like.system.file.infra.file.LocalFileRepository;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.user.domain.vo.AccountSpec;
import com.like.system.user.domain.vo.SystemUserImage;
import com.like.system.user.domain.vo.UserPassword;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "comuser")
public class SystemUser extends AbstractAuditEntity implements UserDetails {	
	
	private static final long serialVersionUID = -4328973281359262612L;

	/**
	 * 조직코드 + 직원번호
	 */
	@Id
	@Column(name="USER_ID")
	String id;
	
	@Column(name="ORG_CD")
	String organizationCode;
	
	@Column(name="STAFF_NO")
	String staffNo;
	
	@Column(name="USER_NAME")
	String name;
			
	@Embedded
	UserPassword password;
		
	@Embedded
	AccountSpec accountSpec;	
	
	@Column(name="MOBILE_NUM")
	String mobileNum;
	
	@Column(name="EMAIL")
	String email;
				
	//@Column(name="FK_FILE")
	//String image;
	@Embedded
	SystemUserImage image;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "DEPT_CD", nullable = true)
	Dept dept;
		
	@Setter
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="COMUSERAUTHORITY",
    		joinColumns= @JoinColumn(name="USER_ID"),
    		inverseJoinColumns=@JoinColumn(name="AUTHORITY_NAME"))	
	Set<Authority> authorities = new LinkedHashSet<>();
			
	@Setter
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="COMUSERMENUGROUP",
    		joinColumns= @JoinColumn(name="USER_ID"),
    		inverseJoinColumns=@JoinColumn(name="MENU_GROUP_ID"))	
	Set<MenuGroup> menuGroupList = new LinkedHashSet<>();		
		
	@Builder
	public SystemUser(String id
					 ,String organizationCode
					 ,String staffNo					 
					 ,String name					 
					 ,UserPassword password
					 ,Dept dept
					 ,String mobileNum
					 ,String email
					 ,AccountSpec accountSpec
					 ,Set<Authority> authorities
					 ,Set<MenuGroup> menuGroupList) {		
		this.id = id;
		this.organizationCode = organizationCode;
		this.staffNo = staffNo;
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
	
	public void modifyEntity(String organizationCode
							,String staffNo
			 				,String name					 				
							,String mobileNum
							,String email							 
							,Dept dept
							,Set<Authority> authorities
							,Set<MenuGroup> menuGroupList) {
		this.organizationCode = organizationCode;
		this.staffNo = staffNo;
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
	
	public Set<Authority> getAuthoritiesList() {
		return this.authorities;
	}
			
	@Override	
	public String getUsername() {		
		return id;
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
			this.authorities = new LinkedHashSet<>();
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
	
	public String getImage() {
		if (this.image == null) return null;
		
		return this.image.getImage();
	}
	
	public String changeImage(LocalFileRepository localFileRepository, MultipartFile sourceFile) {
		if (this.image == null) this.image = new SystemUserImage(localFileRepository);		
		
		return this.image.changeImage(localFileRepository, sourceFile);
	}	
	
}
