package io.gig.catchreview.core.domain.mark.review.dto;

import io.gig.catchreview.core.domain.mark.review.ReviewCard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author : Jake
 * @date : 2021-12-01
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class ReviewCardDto {

    private Long reviewCardId;

    private String reviewImg;

    private String reviewText;

    public ReviewCardDto(ReviewCard rc) {
        this.reviewCardId   = rc.getId();
        this.reviewImg      = rc.getReviewImg();
        this.reviewText     = rc.getReviewText();
    }
}
