package io.gig.catchreview.core.domain.menu;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.menu.types.MenuType;
import io.gig.catchreview.core.domain.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static io.gig.catchreview.core.domain.menu.QMenu.menu;
import static io.gig.catchreview.core.domain.role.QRole.role;

import java.util.List;
import java.util.Set;

/**
 * @author : Jake
 * @date : 2021-08-21
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class MenuQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Menu> getMenuHierarchyByRoles(MenuType menuType, Set<Role> roles) {
        return jpaQueryFactory.selectDistinct(menu)
                .from(menu)
                .join(menu.roles, role).fetchJoin()
                .where(defaultCondition())
                .where(menu.parent.isNull())
                .where(menu.menuType.eq(menuType))
                .where(menu.activeYn.eq(YnType.Y))
                .where(menu.displayYn.eq(YnType.Y))
                .where(role.in(roles))
                .orderBy(menu.sortOrder.asc(), menu.id.asc())
                .fetch();
    }

    public List<Menu> getAllMenuHierarchy(MenuType menuType) {

        return jpaQueryFactory.selectDistinct(menu)
                .from(menu)
                .join(menu.roles, role).fetchJoin()
                .where(defaultCondition())
                .where(menu.parent.isNull())
                .where(menu.menuType.eq(menuType))
                .orderBy(menu.sortOrder.asc(), menu.id.asc())
                .fetch();
    }

    private BooleanExpression defaultCondition() {
        return menu.deleteYn.eq(YnType.N);
    }
}
