package com.like.system.webresource.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebResourceRepository extends JpaRepository<WebResource, String> {

}
