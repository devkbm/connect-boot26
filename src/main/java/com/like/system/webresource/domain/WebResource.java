package com.like.system.webresource.domain;

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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@Entity
@Table(name = "comresource")
@EntityListeners(AuditingEntityListener.class)
public class WebResource extends AbstractAuditEntity implements Serializable{	
	
	private static final long serialVersionUID = 4402275274864737663L;

	@Id
	@Column(name="RESOURCE_ID")
	private String id;
	
	@Column(name="RESOURCE_NAME")
	private String name; 
		
	@Column(name="RESOURCE_TYPE")
	private String type; 
	
	@Column(name="URL")
	private String url;
	
	@Column(name="DESCRIPTION")
	private String description;	
		
	@Builder
	public WebResource(String resourceId, String resourceName, String resourceType, String url, String description) {
		super();
		this.id = resourceId;
		this.name = resourceName;
		this.type = resourceType;
		this.url = url;
		this.description = description;
	}	
		
	public void modifyEntity(String resourceName
							,String resourceType
							,String url
							,String description) {
		this.name = resourceName;
		this.type = resourceType;
		this.url = url;
		this.description = description;
	}
		
}
