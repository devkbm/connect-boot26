package com.like.system.core.dto;

import java.io.Serializable;

/**
 * Html Select Tag 내 Option Tag에 사용될 Record <br>
 * 
 */
public record HtmlSelectOptionRecord(String label, String value) implements Serializable {
		
}
