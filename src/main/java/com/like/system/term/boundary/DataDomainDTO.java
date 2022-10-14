package com.like.system.term.boundary;

import com.like.system.term.domain.DataDomainDictionary;
import com.like.system.term.domain.Database;

import lombok.AccessLevel;
import lombok.Builder;

public class DataDomainDTO {

	@Builder(access = AccessLevel.PRIVATE)
	public static record FormDataDomain(
			String organizationCode,
			String clientAppUrl,
			String domainId,
			String database,
			String domainName,
			String dataType,
			String comment
			) {
		public DataDomainDictionary newEntity() {	
			
			DataDomainDictionary entity = DataDomainDictionary.builder()
															  .database(Database.valueOf(database))
															  .domainName(domainName)
															  .dataType(dataType)
															  .comment(comment)
															  .build();
			
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
		
		public void modifyEntity(DataDomainDictionary entity) {
			entity.modify(dataType, comment);
		}
		
		public static FormDataDomain convert(DataDomainDictionary entity) {
			return FormDataDomain.builder()
								 .domainId(entity.getId())
								 .database(entity.getDatabase().name())
								 .domainName(entity.getDomainName())
								 .dataType(entity.getDataType())
								 .comment(entity.getComment())
								 .build(); 
		}
	}
}
