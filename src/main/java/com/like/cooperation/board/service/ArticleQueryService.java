package com.like.cooperation.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.board.boundary.ArticleDTO;
import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.ArticleQueryRepository;
import com.like.cooperation.board.infra.mapper.BoardMapper;

@Service
@Transactional(readOnly=true)
public class ArticleQueryService {

	private ArticleQueryRepository articleRepository;    
    private BoardMapper boardMapper;
    
    public ArticleQueryService(ArticleQueryRepository articleRepository
    						  ,BoardMapper boardMapper) {
    	this.articleRepository = articleRepository;
    	this.boardMapper = boardMapper;    	
    }
    
	public List<Article> getAritlceList(ArticleDTO.Search condition) {
		return articleRepository.getArticleList(condition.getBooleanBuilder());
	}
	
	public List<Map<String,Object>> getArticleList(ArticleDTO.Search condition) {
		return boardMapper.getArticleList(condition);
	}
    
}
