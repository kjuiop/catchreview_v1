package io.gig.catchreview.core.domain.mark.review;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.mark.review.dto.ReviewDetailDto;
import io.gig.catchreview.core.domain.mark.review.dto.ReviewListDto;
import io.gig.catchreview.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.text.MessageFormat;

import static io.gig.catchreview.core.domain.mark.mark.QMarkDetail.markDetail;
import static io.gig.catchreview.core.domain.mark.review.QReview.review;

/**
 * @author : Jake
 * @date : 2021-11-28
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class ReviewQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<ReviewListDto> getReviewPageList(Long markDetailId, PageRequest pageRequest, Long id) {

        QueryResults<ReviewListDto> fetchResults = this.queryFactory
                .select(Projections.constructor(ReviewListDto.class, review))
                .from(review)
                .where(notDeleted()
                        , eqMarkDetailId(markDetailId)
                )
                .orderBy(review.createdAt.desc())
                .limit(pageRequest.getPageSize())
                .offset(pageRequest.getOffset())
                .fetchResults();

        return new PageImpl<>(fetchResults.getResults(), pageRequest, fetchResults.getTotal());
    }

    public ReviewDetailDto getReviewDetailDto(Long markDetailId, Long reviewId) {
        ReviewDetailDto fetch = this.queryFactory
                .select(Projections.constructor(ReviewDetailDto.class, review))
                .from(review)
                .where(
                        notDeleted()
                        , eqMarkDetailId(markDetailId)
                        , eqReviewId(reviewId)
                )
                .fetchOne();

        if (fetch == null) {
            throw new NotFoundException(MessageFormat.format("{0} 해당 내용을 찾을 수 없습니다.", reviewId));
        }

        return fetch;
    }

    private BooleanExpression notDeleted() {
        return review.deleteYn.eq(YnType.N);
    }

    private BooleanExpression eqMarkDetailId(Long id) {
        if (id == null) {
            return null;
        }

        return markDetail.id.eq(id);
    }

    private BooleanExpression eqReviewId(Long id) {
        if (id == null) {
            return null;
        }

        return review.id.eq(id);
    }
}
