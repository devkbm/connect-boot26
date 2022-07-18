package com.like.cooperation.board.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardBookmarkRepository extends JpaRepository<BoardBookmark, Long> {

	List<BoardBookmark> findByUserIdOrderBySeqAsc(String userId);
}
