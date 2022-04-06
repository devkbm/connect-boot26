package com.like.cooperation.board.domain;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.*;
import com.like.system.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

/**
 * <p>게시판 클래스</p>
 * 
 * [상세내용] <br>
 *   1. <br>
 * [제약조건] <br>
 *   1. <br>
*/
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@Entity
@Table(name = "GRWBOARD")
@EntityListeners(AuditingEntityListener.class)
public class Board extends AuditEntity {		
	
	/**
	 * 게시판 키
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_BOARD")
	Long pkBoard;
	    
	/**
	 * 상위 게시판
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE}, optional = true)
	@JoinColumn(name="PPK_BOARD")
	Board parent;
			
	@Enumerated(EnumType.STRING)
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@Comment("게시판_타입")
	@Column(name="BOARD_TYPE")
    BoardType boardType;
		
	@Comment("게시판 명")
	@Column(name="BOARD_NAME")
    String boardName;             
        
	@Comment("게시판_설명")
	@Column(name="BOARD_DESC")
	String description;    	
	    		
	@Comment("사용여부")
	@Column(name="USE_YN")
	Boolean useYn;        		      
		
	@Comment("출력순서")
	@Column(name="SEQ")
	Long sequence;	
		
	@Comment("게시글 갯수")
	@Column(name="ARTICLE_CNT")
	Long articleCount;

	/**
	 * 게시글 리스트
	 */
	@Singular(value="articles")
    @OneToMany(mappedBy = "board")          
    List<Article> articles;           
    	
	public Board(BoardType boardType
				,String boardName
				,String description
				) {
		this.boardType = boardType;
		this.boardName = boardName;
		this.description = description; 
		this.useYn = true;
		this.sequence = 0L;
		this.articleCount = 0L;
	}
	
	public void modifyEntity(Board parent
						    ,BoardType boardType
						    ,String boardName
						    ,String description						    
						    ,Boolean useYn
						    ,long sequence) {
		this.parent = parent;
		this.boardType = boardType;
		this.boardName = boardName;
		this.description = description;		
		this.useYn = useYn;
		this.sequence = sequence;		
	}
	
    public boolean hasParentBoard() {    	    		    		
    	return this.parent != null ? true : false;
	}
    
    public void setParentRoot() {
    	
    }
    
    public void setParent(Board board) {
    	this.parent = board;
    }
    
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public void addArticle(Article article) {
		this.articles.add(article);		
		
		if (article.board != this) {	// 무한루프에 빠지지 않도록 체크
			article.setBoard(this);
		}
	}
	
}