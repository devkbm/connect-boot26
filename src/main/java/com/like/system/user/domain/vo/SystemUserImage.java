package com.like.system.user.domain.vo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.infra.file.LocalFileRepository;
import com.like.system.file.infra.file.LocalFileRepository.FileUploadLocation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class SystemUserImage implements Serializable {
	
	private static final long serialVersionUID = 42161337448472145L;

	@Column(name="FK_FILE")
	String image;
	
	@Transient
	private LocalFileRepository localFileRepository;
		
	public SystemUserImage(LocalFileRepository localFileRepository) {
		this.localFileRepository = localFileRepository;				
	}	
		
	public String changeImage(LocalFileRepository localFileRepository, MultipartFile sourceFile) {
					
		if (this.image != null) {
			deleteExistingImage(localFileRepository, this.image);
		}
					
		this.image = uploadIamge(localFileRepository, sourceFile);
		
		return this.image;
	}
	
	private void deleteExistingImage(LocalFileRepository localFileRepository, String fileName) {
		try {
			localFileRepository.deleteStaticFile(fileName);
		} catch (FileNotFoundException e) {				
			e.printStackTrace();
		}
	}
	
	private String uploadIamge(LocalFileRepository localFileRepository, MultipartFile sourceFile) {
		String fileName = UUID.randomUUID().toString();
		try {
			localFileRepository.fileTransfer(sourceFile, fileName, FileUploadLocation.STATIC_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
}
