package com.like.cooperation.board.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class ArticleContents {

	@Comment("제목")
	@Column(name="TITLE")
	String title;
        
	@Comment("내용")
	@Column(name="CONTENTS")
    String contents;
	
	public ArticleContents(String title
						  ,String contents) {
		if (title == null || title == "") throw new IllegalArgumentException("게시글 제목은 필수 입력값입니다.");
		
		this.title = title;
		this.contents = contents;
	}
}
