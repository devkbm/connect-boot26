package com.like.system.address.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * src/main/resources/application-roadaddress.properties 
 */
@Component
@Data
@ConfigurationProperties(prefix = "roadaddress")
public class RoadAddressProperties {
	String confmKey;
	String apiUrl;
}

/*
//레코드 인식하지 않아서 주석 처리
@ConstructorBinding
@ConfigurationProperties(prefix = "roadaddress")
public record RoadAddressProperties(String confmKey, String apiUrl) {

}
*/
