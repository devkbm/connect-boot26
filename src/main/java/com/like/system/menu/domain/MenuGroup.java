package com.like.system.menu.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "commenugroup")
@EntityListeners(AuditingEntityListener.class)
public class MenuGroup extends AbstractAuditEntity implements Serializable {		
	
	private static final long serialVersionUID = -638113137072530575L;

	@Id
	@Column(name="menu_group_code")
	String menuGroupCode;
	
	@Column(name="menu_group_name")
	String menuGroupName; 
		
	@Column(name="description")
	String description;
		
	@OneToMany(mappedBy = "menuGroup", cascade = CascadeType.ALL, orphanRemoval = true)          
    List<Menu> menuList = new ArrayList<Menu>();
			
	@Builder
	public MenuGroup(String menuGroupCode, String menuGroupName, String description) {	
		this.menuGroupCode = menuGroupCode;
		this.menuGroupName = menuGroupName;
		this.description = description;
	}	
	
	/**
	 * @param menuGroupName
	 * @param description
	 */
	public void modifyEntity(String menuGroupName
							,String description) {
		this.menuGroupName = menuGroupName;
		this.description = description;
	}
	
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
	public void addMenu(Menu menu) {
		this.menuList.add(menu);
	}
	
	public void deleteMenu(Menu menu) {
		this.menuList.remove(menu);
	}
	
}
