package com.like.system.user.domain.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.like.system.user.domain.ProfilePictureRepository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class SystemUserProfilePicture implements Serializable {
	
	private static final long serialVersionUID = 42161337448472145L;

	@Column(name="FK_FILE")
	String image;
	
	@Transient
	private ProfilePictureRepository repository;
		
	public SystemUserProfilePicture(ProfilePictureRepository repository) {
		this.repository = repository;				
	}	
		
	public String changeImage(ProfilePictureRepository localFileRepository, MultipartFile sourceFile) {
					
		if (this.image != null) {
			deleteExistingImage(localFileRepository, this.image);
		}
					
		this.image = uploadIamge(localFileRepository, sourceFile);
		
		return this.image;
	}
	
	private void deleteExistingImage(ProfilePictureRepository localFileRepository, String fileName) {		
		localFileRepository.delete(fileName);		
	}
	
	private String uploadIamge(ProfilePictureRepository localFileRepository, MultipartFile sourceFile) {		
		return localFileRepository.upload(sourceFile);
	}
}
