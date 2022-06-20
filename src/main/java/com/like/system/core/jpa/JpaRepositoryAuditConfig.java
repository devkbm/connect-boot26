package com.like.system.core.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.like.system.core.audit.SpringSecurityAuditorAware;
import com.like.system.core.jpa.domain.AuditorDetails;

@Configuration
@EnableJpaAuditing
public class JpaRepositoryAuditConfig {
	
	@Bean
	public AuditorAware<AuditorDetails> auditorProvider() {
	    return new SpringSecurityAuditorAware(); // AuditorAware 의 구현체 객체 생성	    
	}
}
