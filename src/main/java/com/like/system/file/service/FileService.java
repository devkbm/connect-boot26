package com.like.system.file.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.domain.FileInfo;
import com.like.system.file.domain.FileInfoRepository;
import com.like.system.file.infra.file.LocalFileRepository;
import com.like.system.file.infra.file.LocalFileRepository.FileUploadLocation;
import com.like.system.file.util.FileConverterUtil;

@Service
public class FileService {
			
	private FileInfoRepository fileInfoRepository;			
	private LocalFileRepository localFileRepository;	
	
	public FileService(FileInfoRepository fileInfoRepository
			          ,LocalFileRepository localFileRepository) {
		this.fileInfoRepository = fileInfoRepository;
		this.localFileRepository = localFileRepository;
	}
	
	@Transactional
	public FileInfo uploadFile(MultipartFile sourceFile, String userId, String pgmId) throws FileNotFoundException, IOException {
									
		String uuid = UUID.randomUUID().toString();
		
		fileTransefer(sourceFile, uuid, FileUploadLocation.LOCAL_PATH);
		
		FileInfo file = createFileInfo(sourceFile, uuid, userId, pgmId);		
												
		return fileInfoRepository.save(file);		
	}
	
	@Transactional
	public List<FileInfo> uploadFile(List<MultipartFile> sourceFiles, String userId, String pgmId) throws FileNotFoundException, IOException {
		
		List<FileInfo> rtn = new ArrayList<FileInfo>();
		
		for (MultipartFile multipartFile : sourceFiles) {			
										
			String uuid = UUID.randomUUID().toString();
			
			fileTransefer(multipartFile, uuid, FileUploadLocation.LOCAL_PATH);
			
			FileInfo file = createFileInfo(multipartFile, uuid, userId, pgmId);	
			
			rtn.add(fileInfoRepository.save(file));
		}
												
		return rtn; 		
	}
		
	
	public void downloadFile(File file, HttpServletResponse response) throws FileNotFoundException, IOException {				
		FileConverterUtil.fileToStream(file, response.getOutputStream());			
	}
	
	public FileInfo downloadFile(HttpServletResponse response, String pk) throws FileNotFoundException, IOException {		
		FileInfo file = getFileInfo(pk);
		
		FileConverterUtil.fileToStream(new File(file.getPath(), file.getUuid()), response.getOutputStream());
		
		return file;
	}
	
	@Transactional	
	public void downloadFile(FileInfo fileInfo, OutputStream os) throws FileNotFoundException, IOException {		
		File file = new File(fileInfo.getPath(), fileInfo.getUuid());
		
		FileConverterUtil.fileToStream(file, os);
		
		// 다운로드 카운트 + 1
		fileInfo.plusDownloadCount();
		
		fileInfoRepository.save(fileInfo);
	}		
	
	@Transactional
	public void deleteFile(FileInfo fileInfo) throws FileNotFoundException {
		
		localFileRepository.deleteFile(new File(fileInfo.getPath(), fileInfo.getUuid()));
		
		fileInfoRepository.delete(fileInfo);											
	}
	
	public void deleteStaticFile(String fileName) throws FileNotFoundException {
		localFileRepository.deleteStaticFile(fileName);
	}
	
	public FileInfo getFileInfo(String id) {
		UUID uuid = UUID.fromString(id);
		return fileInfoRepository.findById(uuid).orElse(null);
	}
	
	public List<FileInfo> getFileInfoList(List<String> id) {
		List<UUID> uuids = id.stream().map(e -> UUID.fromString(e)).toList();
		return fileInfoRepository.findAllById(uuids);
	}
	
	public String fileTransefer(MultipartFile sourceFile, String fileName, FileUploadLocation location) throws FileNotFoundException, IOException {
		return localFileRepository.fileTransfer(sourceFile, fileName, location);
	}
	
	public String downloadBase64(String id) throws FileNotFoundException, IOException {
		UUID uid = UUID.fromString(id);
		FileInfo info = fileInfoRepository.findById(uid).orElse(null);					
		File file = info.getFile();
		
		return FileConverterUtil.getBase64String(file);		
	}
	
	public File getStaticPathFile(String fileName) {
		return localFileRepository.getStaticPathFile(fileName);
	}
	
	private FileInfo createFileInfo(MultipartFile sourceFile, String uuid, String userId, String appUrl) {
						
		return FileInfo.builder()
					   .uuid(uuid)
				       .path(localFileRepository.getLocalUploadPath())
				       .fileName(sourceFile.getOriginalFilename())
				       .size(sourceFile.getSize())
				       .contentType(sourceFile.getContentType())
				       .userId(userId)
				       .appUrl(appUrl)
				       .build();
	}
	
}
