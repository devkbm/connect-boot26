package com.like.system.file.domain;

import java.util.UUID;

import com.fasterxml.uuid.Generators;

/**
 * 참조 URL <br>
 * https://developer111.tistory.com/83 <br>
 * https://github.com/cowtowncoder/java-uuid-generator
 */
public class FileIdGenerator {
	
	public static UUID generateSequencialUUID() {
		UUID uuid = Generators.timeBasedGenerator().generate();
		String[] uuidArr = uuid.toString().split("-");
		String uuidStr = uuidArr[2]+uuidArr[1]+uuidArr[0]+uuidArr[3]+uuidArr[4];
		StringBuffer sb = new StringBuffer(uuidStr);
		sb.insert(8, "-");
		sb.insert(13, "-");
		sb.insert(18, "-");
		sb.insert(23, "-");
		return UUID.fromString(sb.toString());
	}
}
