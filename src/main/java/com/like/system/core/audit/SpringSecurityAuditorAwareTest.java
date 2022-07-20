package com.like.system.core.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.like.system.core.jpa.domain.AuditorDetails;

/**
 * @DataJpaTest 테스트용 클래스
 */
public class SpringSecurityAuditorAwareTest implements AuditorAware<AuditorDetails> {
    
    @Override
    public Optional<AuditorDetails> getCurrentAuditor() {
        
        AuditorDetails currentAuditor = new AuditorDetails(this.getUserId(), "local");               

        return Optional.of(currentAuditor);
    }
    
    private String getUserId() {
    	return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    
}

