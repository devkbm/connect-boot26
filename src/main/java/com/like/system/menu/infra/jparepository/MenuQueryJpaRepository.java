package com.like.system.menu.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.boundary.ResponseMenuHierarchy;
import com.like.system.menu.boundary.MenuDTO;
import com.like.system.menu.boundary.MenuGroupDTO;
import com.like.system.menu.boundary.QResponseMenuHierarchy;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuQueryRepository;
import com.like.system.menu.domain.QMenu;
import com.like.system.menu.domain.QMenuGroup;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuQueryJpaRepository implements MenuQueryRepository {

	private JPAQueryFactory queryFactory;
	private final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;	
	private final QMenu qMenu = QMenu.menu;	
	
	public MenuQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuGroup> getMenuGroupList(MenuGroupDTO.Search condition) {
		return queryFactory
				.selectFrom(qMenuGroup)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public List<MenuGroup> getMenuGroupList(String likeMenuGroupName) {
		return queryFactory
				.selectFrom(qMenuGroup)
				.where(qMenuGroup.name.like(likeMenuGroupName+"%"))
				.fetch();
	}

	@Override
	public List<Menu> getMenuList(MenuDTO.Search condition) {
		return queryFactory
				.selectFrom(qMenu)
					.innerJoin(qMenu.menuGroup, qMenuGroup)
					.fetchJoin()
				.where(condition.getBooleanBuilder())				
				.fetch();
	}
	
	public List<ResponseMenuHierarchy> getMenuRootList(String menuGroupId) {			
						
		JPAQuery<ResponseMenuHierarchy> query = queryFactory
				.select(projections(qMenu))
				.from(qMenu)								
				.where(qMenu.menuGroup.id.eq(menuGroupId)
					.and(qMenu.parent.id.isNull()));													
				
		return query.fetch();
	}
			
	public List<ResponseMenuHierarchy> getMenuChildrenList(String menuGroupId, String parentMenuId) {					
		/*
		Expression<Boolean> isLeaf = new CaseBuilder()										
											.when(qMenu.parent.menuCode.isNotNull()).then(true)
											.otherwise(false).as("isLeaf");
		*/
		
		JPAQuery<ResponseMenuHierarchy> query = queryFactory
				/*.select(Projections.constructor(ResponseMenuHierarchy.class
											, qMenu.menuGroup.menuGroupCode, qMenu.menuCode, qMenu.menuName
											, qMenu.parent.menuCode, qMenu.menuType, qMenu.sequence, qMenu.level, qWebResource.url, isLeaf))*/
				.select(projections(qMenu))
				.from(qMenu)									
				.where(qMenu.menuGroup.id.eq(menuGroupId)
					.and(qMenu.parent.id.eq(parentMenuId)));
																		
		return query.fetch();
	}
	

	// TODO 계층 쿼리 테스트해보아야함 1 루트 노드 검색 : getMenuChildrenList 2. 하위노드 검색 : getMenuHierarchyDTO
	public List<ResponseMenuHierarchy> getMenuHierarchyDTO(List<ResponseMenuHierarchy> list) {
		List<ResponseMenuHierarchy> children = null;
		
		for ( ResponseMenuHierarchy dto : list ) {			
			
			children = getMenuChildrenList(dto.getMenuGroupId(), dto.getKey());
			
			if (children.isEmpty()) {
				dto.setIsLeaf(true);
				continue;
			} else {
				dto.setChildren(children);
				dto.setIsLeaf(false);
				
				// 재귀호출
				getMenuHierarchyDTO(children);
			}
						
		}
		
		return list;
	}
	
	private QResponseMenuHierarchy projections(QMenu qMenu) {		
		
		return new QResponseMenuHierarchy(qMenu.menuGroup.id
										 ,qMenu.id
										 ,qMenu.name
										 ,qMenu.parent.id
										 ,qMenu.type
										 ,qMenu.sequence
										 ,qMenu.level
										 ,qMenu.appUrl);
	}

}
