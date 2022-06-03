package com.like.system.webresource.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.domain.AuditEntity;

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
public class WebResource extends AuditEntity implements Serializable{	
	
	private static final long serialVersionUID = 4402275274864737663L;

	@Id
	@Column(name="resource_code")
	private String resourceCode;
	
	@Column(name="resource_name")
	private String resourceName; 
		
	@Column(name="resource_type")
	private String resourceType; 
	
	@Column(name="url")
	private String url;
	
	@Column(name="description")
	private String description;	
		
	@Builder
	public WebResource(String resourceCode, String resourceName, String resourceType, String url, String description) {
		super();
		this.resourceCode = resourceCode;
		this.resourceName = resourceName;
		this.resourceType = resourceType;
		this.url = url;
		this.description = description;
	}	
		
	public void modifyEntity(String resourceName
							,String resourceType
							,String url
							,String description) {
		this.resourceName = resourceName;
		this.resourceType = resourceType;
		this.url = url;
		this.description = description;
	}
		
}
