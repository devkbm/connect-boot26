package com.like.cooperation.board.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.like.system.file.domain.FileInfo;

public class AttachedFileConverter {

	public static List<ArticleAttachedFile> convert(Article article, List<FileInfo> fileInfoList) {
		
		if (fileInfoList == null || fileInfoList.isEmpty()) return Collections.emptyList();
		
		List<ArticleAttachedFile> list = new ArrayList<>();
		
		for (FileInfo file : fileInfoList) {
			list.add(new ArticleAttachedFile(article, file));
		}
		
		return list;				
	}
}
