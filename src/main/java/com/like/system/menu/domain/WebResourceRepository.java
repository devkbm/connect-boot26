package com.like.system.menu.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebResourceRepository extends JpaRepository<WebResource, String> {

}
