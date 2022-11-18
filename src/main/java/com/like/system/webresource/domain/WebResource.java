package com.like.system.webresource.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;
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
	@Comment("리소스ID")
	@Column(name="RESOURCE_ID")
	String id;
	
	@Comment("리소스명")
	@Column(name="RESOURCE_NAME")
	String name; 
		
	@Comment("리소스분류")
	@Column(name="RESOURCE_TYPE")
	String type; 
		
	@Column(name="URL")
	String url;
	
	@Comment("설명")
	@Column(name="DESCRIPTION")
	String description;	
		
	@Builder
	public WebResource(String resourceId
					  ,String resourceName
					  ,String resourceType
					  ,String url
					  ,String description) {		
		this.id = resourceId;
		this.name = resourceName;
		this.type = resourceType;
		this.url = url;
		this.description = description;
	}	
		
	@Builder(builderMethodName = "modifyBuilder", buildMethodName = "modify")
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
