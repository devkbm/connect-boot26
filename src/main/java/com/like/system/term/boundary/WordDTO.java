package com.like.system.term.boundary;

import com.like.system.term.domain.WordDictionary;

import lombok.AccessLevel;
import lombok.Builder;

public class WordDTO {

	@Builder(access = AccessLevel.PRIVATE)
	public static record FormWord(
			String organizationCode,
			String clientAppUrl,
			String logicalName,
			String logicalNameEng,
			String physicalName,
			String comment
			) {
		public WordDictionary newEntity() {
			WordDictionary entity = new WordDictionary(logicalName
													  ,logicalNameEng
													  ,physicalName
													  ,comment);
			entity.setAppUrl(clientAppUrl);
			
			return entity;
		}
		
		public static FormWord convert(WordDictionary entity) {
			return FormWord.builder()
						   .logicalName(entity.getLogicalName())
						   .logicalNameEng(entity.getLogicalNameEng())
						   .physicalName(entity.getPhysicalName())
						   .comment(entity.getComment())
						   .build(); 
		}
	}
}
