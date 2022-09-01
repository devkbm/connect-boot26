package com.like.system.core.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// https://zepinos.tistory.com/36 참조
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/*
		registry.addResourceHandler("/static/**")
				.addResourceLocations("file://" + "c:\\temp")  
		 */		
	}
}
