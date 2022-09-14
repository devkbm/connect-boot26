package com.like.system.term.boundary;

import com.like.system.term.domain.DataDomainDictionary;
import com.like.system.term.domain.DataDomainDictionary.Database;

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
			String columnSize
			) {
		public DataDomainDictionary newEntity() {			
			return DataDomainDictionary.builder()
									   .database(Database.valueOf(database))
									   .domainName(domainName)
									   .dataType(dataType)
									   .columnSize(columnSize)
									   .build();
		}
		
		public static FormDataDomain convert(DataDomainDictionary entity) {
			return FormDataDomain.builder()
								 .domainId(entity.getId())
								 .database(entity.getDatabase().name())
								 .domainId(entity.getDomainName())
								 .dataType(entity.getDataType())
								 .columnSize(entity.getColumnSize())
								 .build(); 
		}
	}
}
