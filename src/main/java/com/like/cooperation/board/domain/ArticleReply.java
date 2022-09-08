package com.like.cooperation.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "GRWARTICLEREPLY")
@EntityListeners(AuditingEntityListener.class)
public class ArticleReply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ARTICLE_REPLY_ID")
	Long articleReplyId;
	
	@Column(name="USER_ID")
	String userId;
	
	@Comment("내용")
	@Column(name="CONTENTS", length = 4000)
    String contents;
	
	@Comment("댓글 부모ID")
	@Column(name="ARTICLE_REPLY_P_ID")
	Long articleReplyParentId;
	
	/**
	 * 게시글 외래키
	 */           
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARTICLE_ID", nullable=false, updatable=false)
	Article article;
	
	public ArticleReply(Article article, String userId, String contents) {
		this.article = article;
		this.userId = userId;
		this.contents = contents;
	}
	
	public ArticleReply(ArticleReply reply, String userId, String contents) {
		this.article = reply.getArticle();
		this.articleReplyParentId = reply.getArticleReplyId();
		this.userId = userId;
		this.contents = contents;
	}
}
