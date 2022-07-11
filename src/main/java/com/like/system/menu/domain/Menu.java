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
	@Column(name = "MENU_ID")
	String id;
	
	@Column(name="ORG_CD")
	String organizationCode;
		
	@Column(name="MENU_CODE")
	String code;
	
	@Column(name="MENU_NAME")
	String name; 		
				
	@Enumerated(EnumType.STRING)
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@Column(name="MENU_TYPE")
	MenuType type;
	
	@Column(name="APP_URL")
	String appUrl;
	
	@Column(name="SEQ")
	long sequence;
	
	@Column(name="LVL")
	long level;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="P_MENU_ID", nullable = true )
	Menu parent;
	
	@JsonIgnore	
	@ManyToOne
	@JoinColumn(name = "MENU_GROUP_ID", nullable=false, updatable=false)
	MenuGroup menuGroup = new MenuGroup();	
		
	@Builder
	public Menu(@NonNull MenuGroup menuGroup,
				String organizationCode,
				String menuCode, 
				String menuName, 				 			
				MenuType menuType,
				String appUrl,
				long sequence,
				long level) {
		
		this.id = organizationCode + menuCode;
		this.organizationCode = organizationCode;
		this.code = menuCode;
		this.name = menuName;			
		this.type = menuType;
		this.sequence = sequence;
		this.level = level;
		this.menuGroup = menuGroup;
		this.appUrl = appUrl;
	}
	
	/**
	 * 
	 * @param menuName
	 * @param menuType
	 * @param appUrl
	 * @param sequence
	 * @param level
	 * @param parent
	 * @param menuGroup
	 */
	public void modifyEntity(String menuName
							,MenuType menuType
							,String appUrl
							,long sequence
							,long level
							,Menu parent
							,MenuGroup menuGroup) {
		this.name = menuName;
		this.type = menuType;
		this.sequence = sequence;
		this.level = level;
		this.parent = parent;
		this.menuGroup = menuGroup;
		this.appUrl = appUrl;
	}
							
	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}
	
	public void registerAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

}
