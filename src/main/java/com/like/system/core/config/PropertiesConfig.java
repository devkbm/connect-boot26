package com.like.system.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.like.system.core.properties.KakaoProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;


@Configuration
@EnableConfigurationProperties({
	KakaoProperties.class
})
@PropertySource({
	"classpath:properties/kakao.properties"
})
public class PropertiesConfig {

}
