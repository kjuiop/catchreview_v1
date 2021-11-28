package io.gig.catchreview.core.domain.mark.review.dto;

import io.gig.catchreview.core.domain.mark.review.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author : Jake
 * @date : 2021-11-28
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {

    private Long reviewId;

    private String title;

    private String content;

    private String bannerImg;

    private Long markDetailId;

    private LocalDateTime createdAt;

    public ReviewDto(Review r) {
        this.reviewId       = r.getId();
        this.title          = r.getTitle();
        this.content        = r.getContent();
        this.bannerImg      = r.getBannerImg();
        this.markDetailId   = r.getMarkDetail().getId();
        this.createdAt      = r.getCreatedAt();
    }
}
