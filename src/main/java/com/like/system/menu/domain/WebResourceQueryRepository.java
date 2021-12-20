package com.like.system.menu.domain;

import java.util.List;

import com.like.system.menu.boundary.WebResourceDTO;

public interface WebResourceQueryRepository {

	List<WebResource> getResourceList(WebResourceDTO.SearchWebResource condition);
}
