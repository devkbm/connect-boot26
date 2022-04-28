package com.like.cooperation.board.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
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

import com.like.system.core.domain.AuditEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>게시판 즐겨찾기 클래스</p>
 * 
 * [상세내용] <br>
 *   1. <br>
 * [제약조건] <br>
 *   1. <br>
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "GRWBOARDBOOKMARK")
@EntityListeners(AuditingEntityListener.class)
public class BoardBookmark extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -1585368113519480228L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_BOOKMARK")
	Long pkBookmark;
	
	@Comment("게시판 ID")
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="FK_BOARD")
	Board board;
	
	@Comment("사용자 ID")
	@Column(name="USER_ID")
	String userId;
				
	@Comment("순번")
	@Column(name="SEQ")
	Long seq;
		
	public BoardBookmark(Board board
						,String userId) {		
		if (board == null) throw new IllegalArgumentException("게시판이 존재하지 않습니다.");
		
		this.board 	= board;
		this.userId = userId;		
		this.seq 	= 0L;
	}
	
	public void changeSequence(long seq) {
		this.seq = seq;
	}
}
