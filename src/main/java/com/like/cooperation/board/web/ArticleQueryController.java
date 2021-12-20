package com.like.cooperation.board.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.like.cooperation.board.boundary.ArticleDTO;
import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.service.ArticleQueryService;
import com.like.system.core.web.util.WebControllerUtil;

@Controller
public class ArticleQueryController {

	private ArticleQueryService service;
	
	public ArticleQueryController(ArticleQueryService service) {
		this.service = service;		
	}
	
	@GetMapping("/api/grw/board/article")
	public ResponseEntity<?> getArticleList(ArticleDTO.SearchArticle condition) {
																	
		List<Article> list = service.getAritlceList(condition);  							
		
		List<ArticleDTO.ResponseArticle> dtoList = list.stream()
													   .map(e -> ArticleDTO.ResponseArticle.converDTO((e)))
													   .collect(Collectors.toList());		
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
}
