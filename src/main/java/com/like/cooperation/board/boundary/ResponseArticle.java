package com.like.cooperation.board.boundary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.ArticleRepository;
import com.like.system.core.util.SessionUtil;
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
public class ResponseArticle {

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
	
	
	public static ResponseArticle converDTO(Article entity) {
		
    	if (entity == null) return null;
    	
		List<FileInfo> fileInfoList = entity.getAttachedFileInfoList();
		List<FileResponseDTO> responseList = convertFileResponseDTO(fileInfoList);
							
		return ResponseArticle
				 .builder()
				 .createdDt(entity.getCreatedDt())
				 .createdBy(entity.getCreatedBy().getLoggedUser())
				 .modifiedDt(entity.getModifiedDt())
				 .modifiedBy(entity.getModifiedBy().getLoggedUser())
				 .pkArticle(entity.getPkArticle())
				 .ppkArticle(entity.getPpkArticle())							 
				 .userName(entity.getUserName())
				 .fkBoard(entity.getBoard().getPkBoard())				
				 .title(entity.getContent().getTitle())
				 .contents(entity.getContent().getContents())
				 .fileList(responseList)			
				 .editable(entity.getEditable(SessionUtil.getUserId()))
				 .build();
	}
	
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
