package com.like.cooperation.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardBookmarkRepository extends JpaRepository<BoardBookmark, Long>, QuerydslPredicateExecutor<BoardBookmark> {

}
