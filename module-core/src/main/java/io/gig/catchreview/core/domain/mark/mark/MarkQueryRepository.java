package io.gig.catchreview.core.domain.mark.mark;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkCoordinateDto;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkDetailDto;
import io.gig.catchreview.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

import static io.gig.catchreview.core.domain.mark.mark.QMark.mark;
import static io.gig.catchreview.core.domain.mark.mark.QMarkDetail.markDetail;

/**
 * @author : Jake
 * @date : 2021-11-13
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class MarkQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<MarkCoordinateDto> getMarkListForMap() {

        BooleanBuilder where = new BooleanBuilder();
        where.and(mark.deleteYn.eq(YnType.N));

        JPAQuery<MarkCoordinateDto> contentQuery = this.queryFactory
                .select(Projections.constructor(MarkCoordinateDto.class,
                        mark))
                .from(mark)
                .where(where)
                ;

        return contentQuery.fetch();
    }

    public MarkDetailDto getMarkDetailDto(Long markDetailId) {
        MarkDetailDto fetch = this.queryFactory
                    .select(Projections.constructor(MarkDetailDto.class, markDetail))
                    .from(markDetail)
                    .where(markDetail.deleteYn.eq(YnType.N)
                            , eqMarkDetailId(markDetailId))
                    .fetchOne();

        if (fetch == null) {
            throw new NotFoundException(MessageFormat.format("{0} 해당 내용을 찾을 수 없습니다.", markDetailId));
        }

        return fetch;
    }

    private BooleanExpression eqMarkDetailId(Long id) {
        return markDetail.id.eq(id);
    }

}
