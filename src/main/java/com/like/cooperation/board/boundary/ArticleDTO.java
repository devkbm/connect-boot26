package com.like.cooperation.board.boundary;

import java.io.Serializable;
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

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
	
	@Data	
	@NoArgsConstructor	
	@AllArgsConstructor
	@Builder
	@ToString
	public static class FormArticleByMuiltiPart implements Serializable {
		
		private static final long serialVersionUID = -6844786437530688768L;
		
		LocalDateTime createdDt;	
		
		String createdBy;
			
		LocalDateTime modifiedDt;
			
		String modifiedBy;
	    	
	    String pkArticle;	
		
		Long ppkArticle;		
			
		@NotEmpty(message="제목은 필수 입력 사항입니다.")
		String title;
	    	
	    String contents;
	    	
	    String pwd;
	    	
	    int hitCount;
	        
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    LocalDate fromDate;
	    	
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    LocalDate toDate;
	    	
	    Integer seq;
	    	
	    Integer depth;
	    	
	    Long fkBoard;
	            
	    @JsonIgnore
	    @Singular(value = "file")
	    List<MultipartFile> file;	 
	    
	    public Article newArticle(Board board) {
			if (this.fromDate == null || this.toDate == null) {
				this.fromDate = LocalDate.now();
				this.toDate = LocalDate.of(9999, 12, 31);
			}
	    	
	    	
			return Article.builder()	
						  .board(board)
						  .ppkArticle(this.ppkArticle)
						  .title(this.title)
						  .contents(this.contents)
						  .period(new LocalDatePeriod(this.fromDate, this.toDate))						  
						  .pwd(this.pwd)
						  .build();
		}
	    
	    public void modifyArticle(Article entity) {
	    	if (this.fromDate == null || this.toDate == null) {
				this.fromDate = entity.getPeriod().getFrom();
				this.toDate = entity.getPeriod().getTo();
			}	    	
	    	
	    	entity.modifyEntity(title
	    					   ,contents
	    					   ,new LocalDatePeriod(fromDate, toDate)
	    					   ,seq);								
		}
	    
	    public boolean isNew() {
	    	return this.getPkArticle() == null ? true : false;
	    }
	}
	
	@Data	
	@NoArgsConstructor	
	@AllArgsConstructor
	@Builder
	@ToString
	public static class FormArticleByJson implements Serializable {
						
		private static final long serialVersionUID = 919127739529051164L;

		LocalDateTime createdDt;	
		
		String createdBy;
			
		LocalDateTime modifiedDt;
			
		String modifiedBy;
	    	
		Long fkBoard;
		
		Long pkArticle;	
		
		Long ppkArticle;		
			
		@NotEmpty(message="제목은 필수 입력 사항입니다.")
		String title;
	    	
	    String contents;
	    	
	    String pwd;
	    	
	    int hitCount;
	        
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    LocalDate fromDate;
	    	
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    LocalDate toDate;
	    	
	    Integer seq;
	    	
	    Integer depth;	    		   
	            	    
	    /**
	     * FileInfo 도메인의 PK 리스트
	     */
	    List<String> attachFile;	   
	    
	    public Article newArticle(Board board) {
	    	if (this.fromDate == null || this.toDate == null) {
				this.fromDate = LocalDate.now();
				this.toDate = LocalDate.of(9999, 12, 31);
			}
	    	
			return Article.builder()	
						  .board(board)
						  .ppkArticle(this.ppkArticle)
						  .title(this.title)
						  .contents(this.contents)
						  .period(new LocalDatePeriod(this.fromDate, this.toDate))	
						  .pwd(this.pwd)
						  .build();
		}
	    
	    public void modifyArticle(Article entity) {
	    	if (this.fromDate == null || this.toDate == null) {
				this.fromDate = entity.getPeriod().getFrom();
				this.toDate = entity.getPeriod().getTo();
			}	   
	    	
	    	entity.modifyEntity(title
	    					   ,contents
	    					   ,new LocalDatePeriod(fromDate, toDate)
	    					   ,seq);								
		}
	}
	
	
	@Data	
	@NoArgsConstructor	
	@AllArgsConstructor
	@Builder
	@ToString
	public static class ResponseArticle implements Serializable {
				
		private static final long serialVersionUID = 7795172502919533138L;

		LocalDateTime createdDt;	
		
		String createdBy;
			
		LocalDateTime modifiedDt;
			
		String modifiedBy;
	    
		String userName;
		
		Long fkBoard;
		
		Long pkArticle;	
		
		Long ppkArticle;		
			
		@NotEmpty(message="제목은 필수 입력 사항입니다.")
		String title;
	    	
	    String contents;
	    	
	    String pwd;
	    	
	    int hitCount;
	        
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    LocalDate fromDate;
	    	
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    LocalDate toDate;
	    	
	    Integer seq;
	    	
	    Integer depth;	    		   
	            	    
	    List<FileResponseDTO> fileList;	  
	    
	    Boolean editable;
	    
	    public static ArticleDTO.ResponseArticle converDTO(Article entity) {
			
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
