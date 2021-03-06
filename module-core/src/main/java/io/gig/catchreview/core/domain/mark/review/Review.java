package io.gig.catchreview.core.domain.mark.review;

import io.gig.catchreview.core.domain.common.BaseTimeEntity;
import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.mark.mark.MarkDetail;
import io.gig.catchreview.core.domain.mark.review.dto.ReviewCreateForm;
import io.gig.catchreview.core.domain.user.member.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Jake
 * @date : 2021-11-28
 */
@Entity
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mark_detail_id", nullable = false)
    private MarkDetail markDetail;

    private String title;

    @Lob
    private String content;

    private String bannerImg;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType showYn = YnType.Y;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType deleteYn = YnType.N;

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<ReviewCard> reviewCardList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_member_id")
    private Member createdByMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_member_id")
    private Member updatedByMember;

    public static Review createByMember(ReviewCreateForm form,
                                        MarkDetail markDetail,
                                        Member loginMember) {

        return Review.builder()
                .markDetail(markDetail)
                .content(form.getContent())
                .bannerImg(form.getBannerImg())
                .createdByMember(loginMember)
                .updatedByMember(loginMember)
                .build();
    }

    public void addReviewCard(List<ReviewCard> reviewCardList) {
        this.reviewCardList = reviewCardList;
    }
}
