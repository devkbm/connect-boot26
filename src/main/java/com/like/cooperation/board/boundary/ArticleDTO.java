package com.like.cooperation.board.boundary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.ArticleContents;
import com.like.cooperation.board.domain.ArticlePassword;
import com.like.cooperation.board.domain.QArticle;
import com.like.system.core.util.SessionUtil;
import com.like.system.file.boundary.FileResponseDTO;
import com.like.system.file.domain.FileInfo;
import com.like.system.file.infra.file.LocalFileRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

public class ArticleDTO {
	
	public record Search(
			Long fkBoard,
			String title,
			String contents
			) {
		private static final QArticle qArticle = QArticle.article;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(qArticle.board.pkBoard.eq(fkBoard))
				.and(likeTitle(this.title))
				.and(likeContents(this.contents));											
			
			return builder;
		}
		
		private BooleanExpression likeTitle(String title) {
			if (!StringUtils.hasText(title)) return null;
			
			
			return qArticle.content.title.like("%"+title+"%");
		}
		
		private BooleanExpression likeContents(String contents) {
			if (!StringUtils.hasText(contents)) return null;
						
			return qArticle.content.contents.like("%"+contents+"%");
		}
	}	
	
	public record FormArticleByMuiltiPart(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			Long fkBoard,
			String pkArticle,
			Long ppkArticle,
			@NotEmpty(message="제목은 필수 입력 사항입니다.")
			String title,
			String contents,
			String pwd,
			int hitCount,						
			Integer depth,
			@JsonIgnore
			List<MultipartFile> file
			) {
		
		public FormArticleByMuiltiPart {			
		}
		
		public Article newArticle(Board board) {									    			
			
			return Article.builder()	
						  .board(board)						  
						  .content(new ArticleContents(title, contents))						  						  					 
						  .password(new ArticlePassword(this.pwd))
						  .build();
		}
	    
	    public void modifyArticle(Article entity) {
	    	entity.modifyEntity(new ArticleContents(title, contents));								
		}
	    
	    public boolean isNew() {
	    	return this.pkArticle() == null ? true : false;
	    }
	}	
	
	public record FormArticleByJson(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,			
			Long fkBoard,
			Long pkArticle,
			Long ppkArticle,
			@NotEmpty(message="제목은 필수 입력 사항입니다.")
			String title,
			String contents,
			String pwd,
			int hitCount,			
			Integer seq,
			Integer depth,
			List<String> attachFile
			) {
		
		public Article newArticle(Board board) {				    				    	
	    	
			return Article.builder()	
						  .board(board)
						  .content(new ArticleContents(title, contents))						  						  
						  .password(new ArticlePassword(this.pwd))
						  .build();
		}
	    
	    public void modifyArticle(Article entity) {	    		  
	    	
	    	entity.modifyEntity(new ArticleContents(title, contents));								
		}
	}	
	
	@Builder
	public static record ResponseArticle(
			LocalDateTime createdDt,
			String createdBy,
			LocalDateTime modifiedDt,
			String modifiedBy,
			String userName,
			Long fkBoard,
			Long pkArticle,
			Long ppkArticle,
			String title,
			String contents,
			String pwd,
			int hitCount,			
			Integer seq,
			Integer depth,
			List<FileResponseDTO> fileList,
			Boolean editable
			) {
		
		public static ArticleDTO.ResponseArticle converDTO(Article entity) {
			
	    	if (entity == null) return null;
	    	
			List<FileInfo> fileInfoList = entity.getAttachedFileInfoList();
			List<FileResponseDTO> responseList = convertFileResponseDTO(fileInfoList);
								
			return ArticleDTO.ResponseArticle
							 .builder()
							 .createdDt(entity.getCreatedDt())
							 .createdBy(entity.getCreatedBy())
							 .modifiedDt(entity.getModifiedDt())
							 .modifiedBy(entity.getModifiedBy())
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
	    
	    private static List<FileResponseDTO> convertFileResponseDTO(List<FileInfo> fileInfoList) {
	    	List<FileResponseDTO> responseList = new ArrayList<>();	
	    	
	    	for (FileInfo fileInfo : fileInfoList) {
				FileResponseDTO dto = FileResponseDTO.builder()
													 .url(fileInfo.getPkFile())
													 .name(fileInfo.getFileName())
													 .status("done")																									
													 .url(LocalFileRepository.fileDownLoadUrl+fileInfo.getPkFile())
													 .build();
				
				responseList.add(dto);				
			}
	    	
	    	return responseList;
	    }
	}
	
	
	
}
