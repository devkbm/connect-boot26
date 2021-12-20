package com.like.cooperation.board.domain;

import java.util.List;

import com.like.system.file.domain.FileInfo;

public class AttachedFileConverter {

	public static List<AttachedFile> convert(Article article, List<FileInfo> fileInfoList) {
		
		if (fileInfoList == null || fileInfoList.isEmpty()) return null;
		
		return fileInfoList.stream()
				.map( v -> new AttachedFile(article, v) )
				.toList();				
	}
}
