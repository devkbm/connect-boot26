package com.like.system.webresource.domain;

public enum WebResourceType {
	APP("어플리케이션"),
	
	STATIC_IMAGE("정적 이미지");
	
	private String label;
	
	private WebResourceType(final String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
}
