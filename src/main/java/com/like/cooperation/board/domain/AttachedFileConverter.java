package com.like.cooperation.board.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.like.system.file.domain.FileInfo;

public class AttachedFileConverter {

	public static List<AttachedFile> convert(Article article, List<FileInfo> fileInfoList) {
		
		if (fileInfoList == null || fileInfoList.isEmpty()) return Collections.emptyList();
		
		List<AttachedFile> list = new ArrayList<>();
		
		for (FileInfo file : fileInfoList) {
			list.add(new AttachedFile(article, file));
		}
		
		return list;				
	}
}
