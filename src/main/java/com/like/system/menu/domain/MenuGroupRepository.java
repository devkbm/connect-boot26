package com.like.system.menu.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuGroupRepository extends JpaRepository<MenuGroup, String> {

}
