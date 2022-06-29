package com.like.system.menu.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.system.core.jpa.domain.AbstractAuditEntity;
import com.like.system.webresource.domain.WebResource;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true, exclude = {"menuGroup"})
@Entity
@Getter
@Table(name = "commenu")
@EntityListeners(AuditingEntityListener.class)
public class Menu extends AbstractAuditEntity implements Serializable {			
	
	private static final long serialVersionUID = -8469789281288988098L;

	@Id
	@Column(name = "menu_code")
	String id;
	
	@Column(name="menu_name")
	String name; 		
				
	@Enumerated(EnumType.STRING)
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@Column(name="menu_type")
	MenuType type;
	
	@Column(name="seq")
	long sequence;
	
	@Column(name="lvl")
	long level;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="p_menu_code", nullable = true )
	Menu parent;
	
	@JsonIgnore	
	@ManyToOne
	@JoinColumn(name = "menu_group_code", nullable=false, updatable=false)
	MenuGroup menuGroup = new MenuGroup();
	
	@OneToOne(optional=true)
	@JoinColumn(name = "resource_code", nullable=true)
	WebResource resource;
		
	@Builder
	public Menu(@NonNull MenuGroup menuGroup,
				String menuCode, 
				String menuName, 				 			
				MenuType menuType, 
				long sequence,
				long level, 				
				WebResource resource) {
		
		this.id = menuCode;
		this.name = menuName;			
		this.type = menuType;
		this.sequence = sequence;
		this.level = level;
		this.menuGroup = menuGroup;
		this.resource = resource;
	}
	
	/**
	 * @param menuName
	 * @param menuType
	 * @param sequence
	 * @param level
	 * @param parent
	 * @param menuGroup
	 * @param resource
	 */
	public void modifyEntity(String menuName
							,MenuType menuType
							,long sequence
							,long level
							,Menu parent
							,MenuGroup menuGroup
							,WebResource resource) {
		this.name = menuName;
		this.type = menuType;
		this.sequence = sequence;
		this.level = level;
		this.parent = parent;
		this.menuGroup = menuGroup;
		this.resource = resource;
	}
							
	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}
	
	public void registerProgram(WebResource resource) {
		this.resource = resource;
	}

}
