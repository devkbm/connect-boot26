package com.like.system.webresource.domain;

import java.util.List;

import com.like.system.webresource.boundary.WebResourceDTO;

public interface WebResourceQueryRepository {

	List<WebResource> getResourceList(WebResourceDTO.SearchWebResource condition);
}
