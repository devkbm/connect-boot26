package com.like.system.menu.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
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
	@Column(name="MENU_GROUP_ID")
	String id;
	
	@Column(name="ORG_CD")
	String organizationCode;
		
	@Column(name="MENU_GROUP_CODE")
	String code;
	
	@Column(name="MENU_GROUP_NAME")
	String name; 
		
	@Column(name="DESCRIPTION")
	String description;		
			
	@Builder
	public MenuGroup(String organizationCode, String code, String name, String description) {	
		this.id = organizationCode + code;
		this.organizationCode = organizationCode;
		this.code = code;
		this.name = name;
		this.description = description;
	}	
	
	/**
	 * @param menuGroupName
	 * @param description
	 */
	public void modifyEntity(String menuGroupName
							,String description) {
		this.name = menuGroupName;
		this.description = description;
	}
	
}
