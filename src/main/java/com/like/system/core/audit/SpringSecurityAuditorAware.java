package com.like.system.core.audit;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.like.system.core.jpa.domain.AuditorDetails;

import javax.servlet.http.HttpServletRequest;

public class SpringSecurityAuditorAware implements AuditorAware<AuditorDetails> {


    private final Logger log = LoggerFactory.getLogger(SpringSecurityAuditorAware.class);

    @Override
    public Optional<AuditorDetails> getCurrentAuditor() {
        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        AuditorDetails currentAuditor = null;
        
        if(request != null) {
            String hostIp = getClientIpAddress(request);            
            currentAuditor = new AuditorDetails(SecurityContextHolder.getContext().getAuthentication().getName(), hostIp);
            return Optional.of(currentAuditor);
        }

        return Optional.of(currentAuditor);
    }

    private static final String[] IP_HEADER_CANDIDATES = {
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP",
        "HTTP_X_FORWARDED_FOR",
        "HTTP_X_FORWARDED",
        "HTTP_X_CLUSTER_CLIENT_IP",
        "HTTP_CLIENT_IP",
        "HTTP_FORWARDED_FOR",
        "HTTP_FORWARDED",
        "HTTP_VIA",
        "REMOTE_ADDR" };

    public String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            log.info("ip : {}", ip);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

}
