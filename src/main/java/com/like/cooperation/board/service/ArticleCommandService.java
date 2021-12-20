package com.like.cooperation.board.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.board.boundary.ArticleDTO;
import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.ArticleCheck;
import com.like.cooperation.board.domain.ArticleCheckRepository;
import com.like.cooperation.board.domain.ArticleRepository;
import com.like.cooperation.board.domain.AttachedFile;
import com.like.cooperation.board.domain.AttachedFileConverter;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.BoardRepository;
import com.like.system.core.util.SessionUtil;
import com.like.system.file.domain.FileInfo;
import com.like.system.file.service.FileService;

@Service
@Transactional
public class ArticleCommandService {
	
	private BoardRepository boardRepository;	
	private FileService fileService;	
	private ArticleRepository repository;
	private ArticleCheckRepository articleCheckRepository;
	
	
	public ArticleCommandService(BoardRepository boardRepository
								,FileService fileService
								,ArticleRepository repository
								,ArticleCheckRepository articleCheckRepository) {
		this.boardRepository = boardRepository;
		this.fileService = fileService;
		this.repository = repository;
		this.articleCheckRepository = articleCheckRepository;
	}		
	
	public Article getArticle(Long id) {
		return repository.findById(id).orElse(null);		
	}
		
	public String saveArticle(ArticleDTO.FormArticleByMuiltiPart dto) throws Exception {
		
		List<FileInfo> fileInfoList = null;
		List<AttachedFile> attachedFileList = null;					
		
		Article article = convertEntity(dto);			
		
		// 첨부파일 저장
		if (!dto.getFile().isEmpty()) {		
			String userId = SessionUtil.getUserId();
			fileInfoList = fileService.uploadFile(dto.getFile(), userId, "board");
			attachedFileList = AttachedFileConverter.convert(article, fileInfoList);
		}
		
		article.setFiles(attachedFileList);												
									 											
		return this.saveArticle(article);
	}	
	
	public String saveArticle(Article article) {
		String pkArticle = repository.saveAndFlush(article).getId().toString(); 						
		
		return pkArticle;
	}
	
	public String saveArticle(ArticleDTO.FormArticleByJson dto) {		 							
		Board board = boardRepository.findById(dto.getFkBoard()).orElse(null); //.orElseThrow(() -> new IllegalAddException("존재 하지 않은 게시판입니다."));
		Article article = null;
		List<FileInfo> fileInfoList = null;
		List<AttachedFile> attachedFileList = null;
		
		// 1. 기존 게시글이 있는지 조회한다. 
		if (dto.getPkArticle() != null) {
			article = repository.findById(dto.getPkArticle()).orElse(null);
		}
		
		// 2. 저장된 파일 리스트를 조회한다.
		if (dto.getAttachFile() != null) {
			fileInfoList = fileService.getFileInfoList(dto.getAttachFile());			
		}
		
		// 3. 게시글 객체를 생성한다.
		if (article == null) {
			article = dto.newArticle(board);
		} else {
			dto.modifyArticle(article);
		}		
		
		// 4. FileInfo를 AttachedFile로 변환한다.
		attachedFileList = AttachedFileConverter.convert(article, fileInfoList);
		
		if (attachedFileList != null) {
			article.setFiles(attachedFileList);
		}
				
		// 5. 게시글 저장 후 id 리턴
		return repository.saveAndFlush(article).getId().toString();
	}

	public void deleteArticle(Article article) {					
		repository.delete(article);
	}
	
	public void deleteArticle(Long id) {					
		repository.deleteById(id);
	}
	
	public void deleteArticle(List<Article> articleList) {					
		repository.deleteAll(articleList);
	}
	
	public Article updateArticleHitCnt(Long pkAriticle, String userId) {		
		// return articleRepository.updateArticleHitCnt(pkAriticle, userId);
		
		Article article = repository.findById(pkAriticle)
									.orElseThrow(() -> new EntityNotFoundException(pkAriticle + " 존재하지 않습니다."));		
		
		article.updateHitCnt();
		
		repository.save(article);
				
		ArticleCheck check = this.articleCheckRepository.findByCreatedByAndArticle(userId, article);
		
		if (check == null) {
			check = new ArticleCheck(article);				
		} else {
			check.updateHitCnt();
		}
		
		this.articleCheckRepository.save(check);
											 		
		return article;					
	}	
	
	private Article convertEntity(ArticleDTO.FormArticleByMuiltiPart dto) {		
		Board board = boardRepository.findById(dto.getFkBoard()).orElse(null); //.orElseThrow(() -> new IllegalAddException("존재 하지 않은 게시판입니다."));		
		Article article = dto.isNew() ? null : repository.findById(Long.parseLong(dto.getPkArticle())).orElse(null);
						
		if (article == null) {
			article = dto.newArticle(board); 
		} else {
			dto.modifyArticle(article);			
		}
		
		return article;
	}
}
