package com.like.cooperation.board.domain;

import java.util.List;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.system.core.domain.AuditEntity;
import com.like.system.core.util.SessionUtil;
import com.like.system.file.domain.FileInfo;

/**
 * <p>게시글 클래스</p>
 * 
 * [상세내용] <br>
 *   1. <br>
 * [제약조건] <br>
 *   1. <br>
 */
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "GRWARTICLE")
@EntityListeners(AuditingEntityListener.class)
public class Article extends AuditEntity {		
	
	/**
	 * 게시글 키
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_ARTICLE")
	Long pkArticle;	
		
	@Comment("게시글 상위키")
	@Column(name="PPK_ARTICLE")
	Long ppkArticle;		
				
	@Embedded
	ArticleContents content;
	
	@Comment("조회 수")
	@Column(name="HIT_CNT")
    int hitCount;
	
	@Comment("출력순서")
	@Column(name="SEQ")
    Integer seq;
        
	@Comment("계층 횟수")
	@Column(name="HIER_DEPTH")
    int depth;			
	
	@Embedded
	ArticlePassword password;
    
	/**
	 * 게시판 외래키
	 */           
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_BOARD", nullable=false, updatable=false)
	Board board;
    
	@Singular(value="articleChecks")
    @OneToMany(mappedBy = "article")
    List<ArticleCheck> articleChecks;
                          	
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    List<AttachedFile> files;
			
	@Formula("(SELECT X.USER_NAME FROM COMUSER X WHERE X.USER_ID = sys_user)")
	String userName;
			
	@Transient
	Boolean editable;
	
	@Builder
	public Article(Board board
			      ,ArticleContents content
			      ,ArticlePassword password
				  ,List<AttachedFile> files) {
		this.board = board;
		this.content = content;
		this.password = password;
		this.files = files;
				
	}
	
	public void modifyEntity(ArticleContents content) {
		this.content = content;				
	}
	
	public Long getId() {
		return this.pkArticle;
	}
				
	public void setBoard(Board board) {
		this.board = board;
		
		if (!board.getArticles().contains(this)) {
			board.getArticles().add(this);
		}
	}
	
	public boolean hasParentArticle() {		
		return this.ppkArticle != this.pkArticle ? true : false;
	}
						
	public void updateHitCnt() {
		this.hitCount = this.hitCount + 1;	
	}	
		
	public List<FileInfo> getAttachedFileInfoList() {
		return this.files.stream()						 
				  		 .map(v -> v.fileInfo)
				  		 .toList();				  		 					 
	}
	
	public void setFiles(List<AttachedFile> files) {
		this.files = files;
	}
	
	public Boolean getEditable() {			
		return isWriter();
	}
	
	private boolean isWriter() {						
		return this.createdBy.equals(SessionUtil.getUserId());		
	}
			
}