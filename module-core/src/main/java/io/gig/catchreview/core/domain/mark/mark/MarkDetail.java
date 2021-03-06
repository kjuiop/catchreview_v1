package io.gig.catchreview.core.domain.mark.mark;

import io.gig.catchreview.core.domain.common.BaseTimeEntity;
import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.mark.diary.Diary;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkCreateForm;
import io.gig.catchreview.core.domain.mark.mark.types.ApplyStatus;
import io.gig.catchreview.core.domain.mark.mark.types.MarkType;
import io.gig.catchreview.core.domain.mark.mark.types.PublishStatus;
import io.gig.catchreview.core.domain.mark.review.Review;
import io.gig.catchreview.core.domain.user.administrator.Administrator;
import io.gig.catchreview.core.domain.user.member.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class MarkDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mark_id", nullable = false)
    private Mark mark;

    @NotNull
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private MarkType markType;

    @Column(nullable = false)
    private String title;

    private String shortDescription;

    @Lob
    private String content;

    private String bannerImg;

    private String zipCode;

    private String address;

    private String addressDetail;

    private String tel;

    private String workTime;

    @Lob
    private String note;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType showYn = YnType.Y;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType deleteYn = YnType.N;

    @Builder.Default
    @OneToMany(mappedBy = "markDetail", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Diary> diaries = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "markDetail", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

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

    public void addDiary(Diary diary) {
        this.diaries.add(diary);
    }

    public static MarkDetail createByMember(MarkCreateForm form, Mark mark, Member createdByMember) {

        MarkDetail detail = MarkDetail.builder()
                            .mark(mark)
                            .markType(form.getMarkType())
                            .title(form.getTitle())
                            .shortDescription(form.getShortDescription())
                            .content(form.getContent())
                            .bannerImg(form.getBannerImg())
                            .createdByMember(createdByMember)
                            .updatedByMember(createdByMember)
                            .build();

        if (MarkType.STORE.equals(detail.getMarkType())) {
            detail.zipCode       = form.getZipCode();
            detail.address       = form.getAddress();
            detail.addressDetail = form.getAddressDetail();
            detail.tel           = form.getTel();
            detail.workTime      = form.getWorkTime();
        }

        return detail;
    }
}
