package com.like.system.core.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {
	
	public static <E> List<E> toList(Iterable<E> iter) {
		List<E> list = new ArrayList<>();
		
		for (E item: iter) {
			list.add(item);
		}		
		return list;
	}
}
