package com.like.cooperation.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.board.boundary.ArticleDTO;
import com.like.cooperation.board.boundary.ResponseArticleClass;
import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.ArticleQueryRepository;
import com.like.cooperation.board.domain.ArticleRepository;
import com.like.cooperation.board.infra.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly=true)
public class ArticleQueryService {

	private ArticleQueryRepository articleRepository;
	private ArticleRepository repository;
    private BoardMapper boardMapper;
    
    public ArticleQueryService(ArticleQueryRepository articleRepository
    					      ,ArticleRepository repository		
    						  ,BoardMapper boardMapper) {
    	this.articleRepository = articleRepository;
    	this.repository = repository;
    	this.boardMapper = boardMapper;    	
    }
    
	public List<Article> getAritlceList(ArticleDTO.Search condition) {
		return articleRepository.getArticleList(condition.getBooleanBuilder());
	}
	
	public List<ResponseArticleClass> getArticleList2(ArticleDTO.Search condition) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("data", condition);
		
		return boardMapper.getArticleList(params);
	}
	
	public Slice<ResponseArticleClass> getAritlceSlice(ArticleDTO.Search condition, Pageable pageable) {
										
		Map<String, Object> params = new HashMap<>();	
		params.put("data", condition);
		params.put("pageable", pageable);

		List<ResponseArticleClass> content = boardMapper.getArticleList(params);
		
		// 첨부파일 추가
		for (ResponseArticleClass dto : content) {
			dto.addFileResponseDTO(repository);
		}			
		
		boolean hasNext = false;
		if (content.size() > pageable.getPageSize()) {
			content.remove(pageable.getPageSize());
			hasNext = true;
		}			
		return new SliceImpl<>(content, pageable, hasNext);
				
	}
    
}
