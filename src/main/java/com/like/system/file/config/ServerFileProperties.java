package com.like.system.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "server-file")
public class ServerFileProperties {
	/**
	 * Web Server Static Resource Path
	 */
	String staticLocation;
	
	/**
	 * Dynamic Resource Path
	 */
	String localLocation;	
	
	/**
	 * Client App에서 다운로드하기 위한 URL 경로
	 */
	String clientDownloadUrl;
}
