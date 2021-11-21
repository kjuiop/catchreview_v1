package io.gig.catchreview.core.domain.mark.diary;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.mark.diary.dto.DiaryDetailDto;
import io.gig.catchreview.core.domain.mark.diary.dto.DiaryListDto;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkCoordinateDto;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkDetailDto;
import io.gig.catchreview.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

import static io.gig.catchreview.core.domain.mark.mark.QMark.mark;
import static io.gig.catchreview.core.domain.mark.mark.QMarkDetail.markDetail;

import static io.gig.catchreview.core.domain.mark.diary.QDiary.diary;

/**
 * @author : Jake
 * @date : 2021-11-13
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class DiaryQueryRepository {

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

    public Page<DiaryListDto> getDiaryPageList(Long markDetailId,
                                               PageRequest pageRequest,
                                               Long memberId) {
        QueryResults<DiaryListDto> fetchResults = this.queryFactory
                        .select(Projections.constructor(DiaryListDto.class, diary))
                        .from(diary)
                        .where(eqMemberId(memberId),
                                eqMarkDetailId(markDetailId))
                        .orderBy(diary.createdAt.desc())
                        .limit(pageRequest.getPageSize())
                        .offset(pageRequest.getOffset())
                        .fetchResults();

        return new PageImpl<>(fetchResults.getResults(), pageRequest, fetchResults.getTotal());
    }

    public DiaryDetailDto getDiaryDetailDto(Long markDetailId, Long diaryId) {
        DiaryDetailDto fetch = this.queryFactory
                .select(Projections.constructor(DiaryDetailDto.class, diary))
                .from(diary)
                .where(
                        diary.deleteYn.eq(YnType.N)
                        , eqMarkDetailId(markDetailId)
                        , eqDiaryId(diaryId))
                .fetchOne();

        if (fetch == null) {
            throw new NotFoundException(MessageFormat.format("{0} 해당 내용을 찾을 수 없습니다.", diaryId));
        }

        return fetch;
    }

    public long getDiaryCount(Long markDetailId) {

        long count = queryFactory.selectFrom(diary)
                .where(
                        diary.deleteYn.eq(YnType.N)
                        , diary.markDetail.id.eq(markDetailId))
                .fetchCount();

        return count;
    }

    private BooleanExpression eqMarkDetailId(Long id) {
        if (id == null) {
            return null;
        }

        return markDetail.id.eq(id);
    }

    private BooleanExpression eqMemberId(Long id) {
        if (id == null) {
            return null;
        }

        return diary.createdByMember.id.eq(id);
    }

    private BooleanExpression eqDiaryId(Long id) {
        if (id == null) {
            return null;
        }

        return diary.id.eq(id);
    }
}
