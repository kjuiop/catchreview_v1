package io.gig.catchreview.core.domain.mark.review;

import io.gig.catchreview.core.domain.common.BaseTimeEntity;
import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.mark.mark.MarkDetail;
import io.gig.catchreview.core.domain.mark.review.dto.ReviewCardCreateForm;
import io.gig.catchreview.core.domain.user.member.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * @author : Jake
 * @date : 2021-12-01
 */
@Entity
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewCard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_card_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Column(length = 1000)
    private String reviewImg;

    @Lob
    private String reviewText;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType deleteYn = YnType.N;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_member_id")
    private Member createdByMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_member_id")
    private Member updatedByMember;

    public static ReviewCard createByMember(Review review,
                                            ReviewCardCreateForm form,
                                            Member loginMember) {
        return ReviewCard.builder()
                .review(review)
                .reviewImg(form.getReviewImg())
                .reviewText(form.getReviewText())
                .createdByMember(loginMember)
                .updatedByMember(loginMember)
                .build();
    }
}
