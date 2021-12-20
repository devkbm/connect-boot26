package com.like.system.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties
public class KakaoProperties {

	private String restapi;
	private String javascript;
	private String clientsecret;
}
