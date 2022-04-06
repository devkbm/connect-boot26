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
public class ArticlePassword {

	@Comment("비밀번호")
	@Column(name="PWD")
    String password;
	
	public ArticlePassword(String password) {
		this.password = password;		
	}
}
