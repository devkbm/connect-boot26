package com.like.system.file.boundary;

import lombok.Builder;

@Builder
public record FileResponseDTO(
		String uid,
		String name,
		String status,
		String response,
		String url
		) {
	
	//String thumbUrl;
	
	//Map<String, String> linkProps;
	//String linkProps;
}
