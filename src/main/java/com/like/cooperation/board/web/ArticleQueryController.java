package com.like.cooperation.board.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.like.cooperation.board.boundary.ArticleDTO;
import com.like.cooperation.board.service.ArticleQueryService;
import com.like.system.core.message.MessageUtil;
import com.like.system.core.web.util.ResponseEntityUtil;

@Controller
public class ArticleQueryController {

	private ArticleQueryService service;
	
	public ArticleQueryController(ArticleQueryService service) {
		this.service = service;		
	}
	
	@GetMapping("/api/grw/board/article")
	public ResponseEntity<?> getArticleList(ArticleDTO.Search condition) {
																			  						
		List<ArticleDTO.ResponseArticle> list = service.getAritlceList(condition)
													   .stream()
													   .map(e -> ArticleDTO.ResponseArticle.converDTO((e)))
													   .toList();		
		
		return ResponseEntityUtil.toList(list											
										,MessageUtil.getQueryMessage(list.size()));
	}
}
