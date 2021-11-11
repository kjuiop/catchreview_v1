package io.gig.catchreview.core.domain.mark;

import io.gig.catchreview.core.domain.common.BaseTimeEntity;
import io.gig.catchreview.core.domain.mark.types.MarkStatus;
import io.gig.catchreview.core.domain.user.administrator.Administrator;
import io.gig.catchreview.core.domain.user.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author : Jake
 * @date : 2021-11-07
 */
@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private int point;

    @Lob
    private String note;

    @Builder.Default
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private MarkStatus status = MarkStatus.PENDING;

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
