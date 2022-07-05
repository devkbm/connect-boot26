package com.like.system.file.infra.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.config.ServerFileProperties;

@Repository
public class LocalFileRepository {
	
	public enum FileUploadLocation {
		STATIC_PATH, LOCAL_PATH
	}	
	private ServerFileProperties properties;
		
	public static String fileDownLoadUrl;		
	
	public LocalFileRepository(ServerFileProperties properties) {
		this.properties = properties;
		LocalFileRepository.fileDownLoadUrl = properties.getClientDownloadUrl(); 
	}	
	
	public String getLocalUploadPath() {	
		return this.properties.getLocalLocation();
	}
	
	public String getStaticUploadPath() {
		return this.properties.getStaticLocation();
	}
			
		
	public String fileTransfer(MultipartFile sourceFile, String fileName, FileUploadLocation location) throws FileNotFoundException, IOException {
		String path = null;		
					
		if ( location == FileUploadLocation.LOCAL_PATH) {
			path = this.getLocalUploadPath();
		} else {
			path = this.getStaticUploadPath();
		}
		
		File file = FileConverterUtil.convertAndCopy(sourceFile, path, fileName);
								
		FileConverterUtil.streamToFile(sourceFile.getInputStream(), file);	
		
		return file.getPath();
	}	
	
	/**
	 * 파일을 삭제한다.
	 * @param file 
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean deleteFile(File file) throws FileNotFoundException {				
		if(!isExists(file)) throw new FileNotFoundException();
						
		return file.delete();		
	}	
	
	public File getStaticPathFile(String fileName) {
		return new File(this.getStaticUploadPath(), fileName);
	}
	
	public boolean deleteStaticFile(String fileName) throws FileNotFoundException {
		File file = this.getStaticPathFile(fileName);
		
		if(!isExists(file)) throw new FileNotFoundException();
		
		return file.delete();
	}	
	
	private boolean isExists(File file) {
		if(file == null || !file.exists()) {
			return false;
		}
		
		return true;
	}
	
}
