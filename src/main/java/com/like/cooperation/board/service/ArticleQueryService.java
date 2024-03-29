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
import com.like.cooperation.board.boundary.ResponseArticle;
import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.domain.ArticleQueryRepository;
import com.like.cooperation.board.domain.ArticleRepository;
import com.like.cooperation.board.infra.mapper.BoardMapper;

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
	
	public List<ResponseArticle> getArticleList2(ArticleDTO.Search condition) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("data", condition);
		
		return boardMapper.getArticleList(params);
	}
	
	public Slice<ResponseArticle> getAritlceSlice(ArticleDTO.Search condition, Pageable pageable) {
										
		Map<String, Object> params = new HashMap<>();	
		params.put("data", condition);
		params.put("pageable", pageable);

		List<ResponseArticle> content = boardMapper.getArticleList(params);
		
		// 첨부파일 추가
		for (ResponseArticle dto : content) {
			dto.addFileResponseDTO(repository);
		}			
		
		// 마지막 데이터인지 여부를 확인하기 위해 +1개를 조회한후 데이터 제거
		boolean hasNext = false;
		if (content.size() > pageable.getPageSize()) {
			content.remove(pageable.getPageSize());
			hasNext = true;
		}			
		return new SliceImpl<>(content, pageable, hasNext);
				
	}
    
}
