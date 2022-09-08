package com.like.cooperation.board.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.system.core.jpa.domain.AbstractAuditEntity;
import com.like.system.file.domain.FileInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>게시글 첨부파일 클래스</p>
 * 
 * [상세내용] <br>
 *   1. <br>
 * [제약조건] <br>
 *   1. <br>
 */
@ToString(exclude= {"article","fileInfo"})
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true, value = {"article","fileInfo"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRWARTICLEFILE")
public class ArticleAttachedFile extends AbstractAuditEntity implements Serializable {
		
		
	private static final long serialVersionUID = 1933620773768936638L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ARTICLE_FILE_ID")
	Long pkArticleFile;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARTICLE_ID", nullable = false)
	Article article; 	
		
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="FILE_ID", nullable = false)
	FileInfo fileInfo;

	public ArticleAttachedFile(Article article, FileInfo fileInfo) {		
		this.article = article;
		this.fileInfo = fileInfo;
	}
		
}


