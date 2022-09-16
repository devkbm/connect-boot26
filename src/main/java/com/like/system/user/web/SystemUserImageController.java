package com.like.system.user.web;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.service.FileService;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.service.SystemUserService;

@Controller
public class SystemUserImageController {
	
	private FileService fileService;
		
	private SystemUserService userService;
		
	public SystemUserImageController(FileService fileService, SystemUserService userService) {
		this.fileService = fileService;
		this.userService = userService;
	}
	
	@GetMapping("/api/system/user/image")
	public HttpServletResponse downloadUserImage(HttpServletResponse response,
											     @RequestParam String userId) throws Exception {
				
		SystemUser user = userService.getUser(userId);			
		
		File file = fileService.getStaticPathFile(user.getImage());
				
		response = this.setDownloadResponseHeader(response, user.getId(), file.length());
		
		fileService.downloadFile(file, response);
			
		return response;
	}
	
	@PostMapping("/api/system/user/image")
	public ResponseEntity<?> changeUserImage(@RequestPart MultipartFile file,
											 String userId) throws Exception {				
												
		String fileName = userService.changeUserImage(userId, file);			
							
		return new ResponseEntity<Map<String,Object>>(setUploadResponseBody(fileName), setUploadResponseHeader(), HttpStatus.OK);
	}
	
	private HttpServletResponse setDownloadResponseHeader(HttpServletResponse response, String fileName, long fileSize) throws Exception {
		
		// get MIME type of the file
		String mimeType= null;
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";	         
		}
		
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLengthLong(fileSize);
		response.setCharacterEncoding("UTF-8");
		
		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment;filename=\"%s\"", new String(fileName.getBytes("UTF-8"), "8859_1"));
		
		response.setHeader(headerKey, headerValue);
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");	
		
		return response;
	}
	
	private Map<String, Object> setUploadResponseBody(String fileName) {		
		Map<String, Object> response = new HashMap<>();
		response.put("data", fileName);
		response.put("status", "done");
				
		return response;
	}
	
	private HttpHeaders setUploadResponseHeader() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		return responseHeaders;		
	}
}
