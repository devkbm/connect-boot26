package com.like.cooperation.board.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.board.boundary.BoardHierarchy;
import com.like.cooperation.board.boundary.QBoardHierarchy;
import com.like.cooperation.board.domain.Board;
import com.like.cooperation.board.domain.BoardQueryRepository;
import com.like.cooperation.board.domain.QBoard;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BoardQueryJpaRepository implements BoardQueryRepository {

	private JPAQueryFactory queryFactory;
	private final QBoard qBoard = QBoard.board;
	
	public BoardQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<Board> getBoardList(Predicate condition) {
		return queryFactory
				.selectFrom(qBoard)
				.where(condition)
				.fetch(); 	
	}

	@Override
	public List<BoardHierarchy> getBoardHierarchy() {
		List<BoardHierarchy> rootList = getBoardHierarchyRootList();
		
		List<BoardHierarchy> rtn =  setLinkBoardHierarchy(rootList);
		
		return rtn;
	}
	
	private List<BoardHierarchy> setLinkBoardHierarchy(List<BoardHierarchy> list) {
		List<BoardHierarchy> children = null;
		
		for ( BoardHierarchy dto : list) {
			
			children = getBoardHierarchyChildrenList(dto.getKey());
			
			if (children.isEmpty()) {	// leaf 노드이면 다음 리스트 검색
				dto.setLeaf(true);
				continue;
			} else {
				
				dto.setChildren(children);
				dto.setLeaf(false);
				
				setLinkBoardHierarchy(children);
			}
		}
		
		return list;
	}
	
	private List<BoardHierarchy> getBoardHierarchyRootList() {									
		
		/*Expression<Boolean> isLeaf = new CaseBuilder()
										.when(qBoard.ppkBoard.isNotNull()).then(true)
										.otherwise(false).as("leaf");*/	
		
		JPAQuery<BoardHierarchy> query = queryFactory
				.select(new QBoardHierarchy(qBoard.pkBoard, qBoard.parent.pkBoard, qBoard.boardType
										   ,qBoard.boardName, qBoard.description))
				.from(qBoard)
				.where(qBoard.isRootNode());
													
						
		return query.fetch();	
	}
	
	private List<BoardHierarchy> getBoardHierarchyChildrenList(Long parentPkBoard) {
		
		JPAQuery<BoardHierarchy> query = queryFactory
				.select(new QBoardHierarchy(qBoard.pkBoard, qBoard.parent.pkBoard, qBoard.boardType
						   				   ,qBoard.boardName, qBoard.description))
				.from(qBoard)
				.where(qBoard.parent.pkBoard.eq(parentPkBoard));								
		
		return query.fetch();
		
	}

}
