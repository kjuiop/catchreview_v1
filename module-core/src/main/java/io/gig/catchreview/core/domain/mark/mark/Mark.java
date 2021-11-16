package io.gig.catchreview.core.domain.mark.mark;

import io.gig.catchreview.core.domain.common.BaseTimeEntity;
import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkCreateForm;
import io.gig.catchreview.core.domain.mark.mark.types.ApplyStatus;
import io.gig.catchreview.core.domain.mark.mark.types.MarkType;
import io.gig.catchreview.core.domain.mark.mark.types.PromotionStatus;
import io.gig.catchreview.core.domain.mark.mark.types.PublishStatus;
import io.gig.catchreview.core.domain.user.administrator.Administrator;
import io.gig.catchreview.core.domain.user.member.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Jake
 * @date : 2021-11-07
 */
@Entity
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Mark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    private Long id;

    @Column(nullable = false)
    private String coordinateX;

    @Column(nullable = false)
    private String coordinateY;

    private int point;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    @Builder.Default
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ApplyStatus applyStatus = ApplyStatus.PENDING;

    @Builder.Default
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private PromotionStatus promotionStatus = PromotionStatus.PENDING;

    @Builder.Default
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private PublishStatus publishStatus = PublishStatus.ME;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType showYn = YnType.Y;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType deleteYn = YnType.N;

    @Builder.Default
    @OneToMany(mappedBy = "mark", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<MarkDetail> markDetails = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_admin_id")
    private Administrator createdByAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_admin_id")
    private Administrator updatedByAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_member_id")
    private Member createdByMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_member_id")
    private Member updatedByMember;

    public void addMarkDetail(MarkDetail detail) {
        this.markDetails.add(detail);
    }

    public static Mark createByMember(MarkCreateForm form, Member loginUser) {
        return Mark.builder()
                .coordinateX(form.getCoordinateX())
                .coordinateY(form.getCoordinateY())
                .createdByMember(loginUser)
                .updatedByMember(loginUser)
                .build();
    }
}
