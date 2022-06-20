package com.like.system.core.jpa.domain;

import java.io.Serializable;
import java.util.Objects;

public final class AuditorDetails implements Serializable {    
	private static final long serialVersionUID = 6038720304169228462L;

	private final String loggedUser;   

    private final String hostIp;

    public AuditorDetails(String loggedUser, String hostIp) {
        this.loggedUser = loggedUser;        
        this.hostIp = hostIp;
    }

    public String getLoggedUser() {
        return loggedUser;
    }
    
    public String getHostIp() {
        return hostIp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditorDetails that = (AuditorDetails) o;
        return Objects.equals(loggedUser, that.loggedUser) &&     
            Objects.equals(hostIp, that.hostIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loggedUser, hostIp);
    }
}