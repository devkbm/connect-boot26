package com.like.system.user.domain;

import org.springframework.web.multipart.MultipartFile;

public interface ProfilePictureRepository {
	String upload(MultipartFile sourceFile);
	
	void delete(String path);		
}
