package com.like.cooperation.board.domain;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Getter;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.hrm.anualleave.domain.model.AnualLeaveId;
import com.like.system.core.jpa.domain.AbstractAuditEntity;

/**
 * <p>게시글 조회여부 클래스</p>
 * 
 * [상세내용] <br>
 *   1. <br>
 * [제약조건] <br>
 *   1. <br>
 */
@JsonAutoDetect
@Getter
@Entity
@Table(name = "GRWARTICLECHECK")
@EntityListeners(AuditingEntityListener.class)
public class ArticleRead extends AbstractAuditEntity implements Serializable {
	
	private static final long serialVersionUID = 6322358555393677284L;
	
	@EmbeddedId
	ArticleReadId id;	
		
	@Comment("조회수")
	@Column(name="hit_cnt")
	Integer hitCount;
        
	/**
	 * 게시판 외래키
	 */        
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_article", nullable=false, updatable=false)
	Article article;

    protected ArticleRead() {}
    
	public ArticleRead(Article article, String staffId) {		
		this.article = article;
		this.hitCount = 0;
	}
			
	public void updateHitCnt() {
		this.hitCount = this.hitCount + 1;
	}
		
			
}
