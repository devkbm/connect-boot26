package com.like.system.menu.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.boundary.ResponseMenuHierarchy;
import com.like.system.menu.boundary.MenuDTO.SearchMenu;
import com.like.system.menu.boundary.MenuGroupDTO.SearchMenuGroup;
import com.like.system.menu.boundary.QResponseMenuHierarchy;
import com.like.system.menu.domain.Menu;
import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.MenuQueryRepository;
import com.like.system.menu.domain.QMenu;
import com.like.system.menu.domain.QMenuGroup;
import com.like.system.menu.domain.QWebResource;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuQueryJpaRepository implements MenuQueryRepository {

	private JPAQueryFactory queryFactory;
	private final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;	
	private final QMenu qMenu = QMenu.menu;
	private final QWebResource qWebResource = QWebResource.webResource;
	
	public MenuQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuGroup> getMenuGroupList(SearchMenuGroup condition) {
		return queryFactory
				.selectFrom(qMenuGroup)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public List<MenuGroup> getMenuGroupList(String likeMenuGroupName) {
		return queryFactory
				.selectFrom(qMenuGroup)
				.where(qMenuGroup.menuGroupName.like(likeMenuGroupName+"%"))
				.fetch();
	}

	@Override
	public List<Menu> getMenuList(SearchMenu condition) {
		return queryFactory
				.selectFrom(qMenu)
					.innerJoin(qMenu.menuGroup, qMenuGroup)
					.fetchJoin()
				.where(condition.getBooleanBuilder())				
				.fetch();
	}
	
	public List<ResponseMenuHierarchy> getMenuRootList(String menuGroupCode) {			
						
		JPAQuery<ResponseMenuHierarchy> query = queryFactory
				.select(projections(qMenu))
				.from(qMenu)
					.leftJoin(qMenu.resource, qWebResource)					
				.where(qMenu.menuGroup.menuGroupCode.eq(menuGroupCode)
					.and(qMenu.parent.menuCode.isNull()));													
				
		return query.fetch();
	}
			
	public List<ResponseMenuHierarchy> getMenuChildrenList(String menuGroupCode, String parentMenuCode) {					
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
					.leftJoin(qMenu.resource, qWebResource)
				.where(qMenu.menuGroup.menuGroupCode.eq(menuGroupCode)
					.and(qMenu.parent.menuCode.eq(parentMenuCode)));
																		
		return query.fetch();
	}
	

	// TODO 계층 쿼리 테스트해보아야함 1 루트 노드 검색 : getMenuChildrenList 2. 하위노드 검색 : getMenuHierarchyDTO
	public List<ResponseMenuHierarchy> getMenuHierarchyDTO(List<ResponseMenuHierarchy> list) {
		List<ResponseMenuHierarchy> children = null;
		
		for ( ResponseMenuHierarchy dto : list ) {			
			if (dto.isLeaf()) { // leaf 노드이면 다음 리스트 검색
				continue;
			} else {				
				children = getMenuChildrenList(dto.getMenuGroupCode(), dto.getKey());
				dto.setChildren(children);
				
				getMenuHierarchyDTO(children);
			}
		}
		
		return list;
	}
	
	private QResponseMenuHierarchy projections(QMenu qMenu) {
		Expression<Boolean> isLeaf = new CaseBuilder()										
				.when(qMenu.parent.menuCode.isNotNull()).then(true)
				.otherwise(false).as("isLeaf");
		
		return new QResponseMenuHierarchy(qMenu.menuGroup.menuGroupCode, qMenu.menuCode, qMenu.menuName
				, qMenu.parent.menuCode, qMenu.menuType, qMenu.sequence, qMenu.level, qWebResource.url, isLeaf);
	}

}
