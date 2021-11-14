package io.gig.catchreview.core.domain.mark.diary;

import io.gig.catchreview.core.domain.common.BaseTimeEntity;
import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.mark.mark.Mark;
import io.gig.catchreview.core.domain.mark.mark.MarkDetail;
import io.gig.catchreview.core.domain.user.administrator.Administrator;
import io.gig.catchreview.core.domain.user.member.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * @author : Jake
 * @date : 2021-11-14
 */
@Entity
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mark_detail_id", nullable = false)
    private MarkDetail markDetail;

    private String title;

    @Lob
    private String content;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType showYn = YnType.Y;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType deleteYn = YnType.N;

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
}
