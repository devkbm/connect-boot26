package com.like.system.file.boundary;

import com.like.system.file.domain.FileInfo;
import com.like.system.file.infra.file.LocalFileRepository;

public record FileResponseDTO(
		String uid,
		String name,
		String status,
		String response,
		String url
		) {
			
	public static FileResponseDTO convert(FileInfo info) {
		
		// url 예시 - http://localhost:8090/common/file/"+info.getPkFile()
		
		return new FileResponseDTO(info.getId()
								  ,info.getFileName()
								  ,"done"
								  ,"success"
								  ,LocalFileRepository.fileDownLoadUrl+info.getId());
	}
}
