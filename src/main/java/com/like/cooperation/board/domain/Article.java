package com.like.cooperation.board.domain;

import java.util.List;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
import com.like.system.core.vo.LocalDatePeriod;
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
@AllArgsConstructor
@Builder
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
				
	@Comment("제목")
	@Column(name="TITLE")
	String title;
        
	@Comment("내용")
	@Column(name="CONTENTS")
    String contents;        
        
	@Comment("조회 수")
	@Column(name="HIT_CNT")
    int hitCount;
        		
	@Embedded
    LocalDatePeriod period;
	    
	@Comment("출력순서")
	@Column(name="SEQ")
    Integer seq;
        
	@Comment("계층 횟수")
	@Column(name="HIER_DEPTH")
    int depth;
			
	@Comment("비밀번호사용여부")
	@Column(name="PWD_YN")
	Boolean pwdYn;
			
	@Comment("비밀번호")
	@Column(name="PWD")
    String pwd;
    
	/**
	 * 게시판 외래키
	 */           
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_BOARD", nullable=false, updatable=false)
	Board board;
    
	@Singular(value="articleChecks")
    @OneToMany(mappedBy = "article")
    List<ArticleCheck> articleChecks;
                          
	@Singular(value="files")
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    List<AttachedFile> files;
			
	@Formula("(SELECT X.USER_NAME FROM COM.COMUSER X WHERE X.USER_ID = sys_user)")
	String userName;
			
	@Transient
	Boolean editable;
	

	/**
	 * @param title
	 * @param contents
	 * @param fromDate
	 * @param toDate
	 * @param seq
	 */
	public void modifyEntity(String title
							,String contents
							,LocalDatePeriod period
							,Integer seq) {
		this.title = title;
		this.contents = contents;
		this.period = period;
		this.seq = seq;
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
				
	public void setSeq(int seq) {
		this.seq = seq;
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