package com.like.cooperation.board.boundary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.ArticleRepository;
import com.like.system.file.boundary.FileResponseDTO;
import com.like.system.file.domain.FileInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseArticleClass {

	LocalDateTime createdDt;
	String createdBy;
	LocalDateTime modifiedDt;
	String modifiedBy;
	String userName;
	Long fkBoard;
	Long pkArticle;
	Long ppkArticle;
	String title;
	String contents;
	String pwd;
	int hitCount;			
	Integer seq;
	Integer depth;
	List<FileResponseDTO> fileList;
	Boolean editable;
	
	public void addFileResponseDTO(ArticleRepository repository) {
		
		Article entity = repository.findById(this.pkArticle).orElse(null);
		
		if (entity == null) return;
		
		List<FileInfo> fileInfoList = entity.getAttachedFileInfoList();
		
		this.fileList = convertFileResponseDTO(fileInfoList);
	}
	
	private static List<FileResponseDTO> convertFileResponseDTO(List<FileInfo> fileInfoList) {
    	List<FileResponseDTO> responseList = new ArrayList<>();	
    	
    	for (FileInfo fileInfo : fileInfoList) {							
			responseList.add(FileResponseDTO.convert(fileInfo));				
		}
    	
    	return responseList;
    }
}
