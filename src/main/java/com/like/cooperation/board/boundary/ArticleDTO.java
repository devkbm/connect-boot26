package com.like.cooperation.board.boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.QArticle;
import com.like.system.core.vo.LocalDatePeriod;
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
			
			return qArticle.title.like("%"+title+"%");
		}
		
		private BooleanExpression likeContents(String contents) {
			if (!StringUtils.hasText(contents)) return null;
			
			return qArticle.contents.like("%"+contents+"%");
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
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			LocalDate fromDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			LocalDate toDate,
			Integer seq,
			Integer depth,
			@JsonIgnore
			List<MultipartFile> file
			) {
		
		public FormArticleByMuiltiPart {
			/*
			if (this.fromDate == null || this.toDate == null) {
				this.fromDate = LocalDate.now();
				this.toDate = LocalDate.of(9999, 12, 31);
			}
			*/
		}
		
		public Article newArticle(Board board) {									    
			LocalDate from = this.fromDate == null ? LocalDate.now() : this.fromDate; 
	    	LocalDate to = this.toDate == null ? LocalDate.of(9999, 12, 31) : this.toDate;
			
			return Article.builder()	
						  .board(board)
						  .ppkArticle(this.ppkArticle)
						  .title(this.title)
						  .contents(this.contents)
						  .period(new LocalDatePeriod(from, to))						  
						  .pwd(this.pwd)
						  .build();
		}
	    
	    public void modifyArticle(Article entity) {
	    	LocalDate from = this.fromDate == null ? LocalDate.now() : this.fromDate; 
	    	LocalDate to = this.toDate == null ? LocalDate.of(9999, 12, 31) : this.toDate;	    	
	    	
	    	entity.modifyEntity(title
	    					   ,contents
	    					   ,new LocalDatePeriod(from, to)
	    					   ,seq);								
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
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			LocalDate fromDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			LocalDate toDate,
			Integer seq,
			Integer depth,
			List<String> attachFile
			) {
		
		public Article newArticle(Board board) {				    			
	    	LocalDate from = this.fromDate == null ? LocalDate.now() : this.fromDate; 
	    	LocalDate to = this.toDate == null ? LocalDate.of(9999, 12, 31) : this.toDate;
	    	
			return Article.builder()	
						  .board(board)
						  .ppkArticle(this.ppkArticle)						  
						  .title(this.title)
						  .contents(this.contents)
						  .period(new LocalDatePeriod(from, to))	
						  .pwd(this.pwd)
						  .build();
		}
	    
	    public void modifyArticle(Article entity) {
	    	LocalDate from = this.fromDate == null ? LocalDate.now() : this.fromDate; 
	    	LocalDate to = this.toDate == null ? LocalDate.of(9999, 12, 31) : this.toDate;	   
	    	
	    	entity.modifyEntity(title
	    					   ,contents
	    					   ,new LocalDatePeriod(from, to)
	    					   ,seq);								
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
			LocalDate fromDate,
			LocalDate toDate,
			Integer seq,
			Integer depth,
			List<FileResponseDTO> fileList,
			Boolean editable
			) {
		
		public static ArticleDTO.ResponseArticle converDTO(Article entity) {
			
	    	if (entity == null) return null;
	    	
			List<FileInfo> fileInfoList = entity.getAttachedFileInfoList();
			List<FileResponseDTO> responseList = convertFileResponseDTO(fileInfoList);
			
			Optional<LocalDatePeriod> period = Optional.ofNullable(entity.getPeriod());
			
			return ArticleDTO.ResponseArticle
							 .builder()
							 .createdDt(entity.getCreatedDt())
							 .createdBy(entity.getCreatedBy())
							 .modifiedDt(entity.getModifiedDt())
							 .modifiedBy(entity.getModifiedBy())
							 .pkArticle(entity.getPkArticle())
							 .ppkArticle(entity.getPpkArticle())
							 .fromDate(period.map(LocalDatePeriod::getFrom).orElse(null))
							 .toDate(period.map(LocalDatePeriod::getTo).orElse(null))
							 .userName(entity.getUserName())
							 .fkBoard(entity.getBoard().getPkBoard())				
							 .title(entity.getTitle())
							 .contents(entity.getContents())
							 .fileList(responseList)			
							 .editable(entity.getEditable())
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
