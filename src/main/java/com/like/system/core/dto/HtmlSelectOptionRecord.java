package com.like.system.core.dto;

import java.util.Arrays;
import java.util.List;

/**
 * Html Select Tag 내 Option Tag에 사용될 Record <br>
 * 
 */
public record HtmlSelectOptionRecord(String label, String value) {
	
	/**
	 * 사용 예제 <br>
	 * List<HtmlSelectOptionRecord> list = HtmlSelectOptionRecord.fromEnum(BoardType.class)
	 */
	public static List<HtmlSelectOptionRecord> fromEnum(Class<? extends HtmlSelectOptionable> obj) {
		return Arrays.stream(obj.getEnumConstants())
					 .map(e -> new HtmlSelectOptionRecord(e.getLabel(), e.getValue()))
					 .toList();
	}
}
