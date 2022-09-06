package com.like.cooperation.board.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ArticleReadId implements Serializable {

	private static final long serialVersionUID = -5781395006298446423L;

	@Column(name="ARTICLE_ID")
	Article articleId;
	
	@Column(name="USER_ID")
	String userId;

}
