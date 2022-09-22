package com.like.system.core.dto;

import java.util.Arrays;
import java.util.List;

/**
 * HTML Select Tag의 Option Tag에 활용 
 */
public interface HtmlSelectOptionable {	
	public String getLabel();
	public String getValue();
	
	public static List<HtmlSelectOptionRecord> fromEnum(Class<? extends HtmlSelectOptionable> obj) {
		return Arrays.stream(obj.getEnumConstants())
					 .map(e -> new HtmlSelectOptionRecord(e.getLabel(), e.getValue()))
					 .toList();
	}
}
