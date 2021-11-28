package io.gig.catchreview.core.domain.mark.review.dto;

import io.gig.catchreview.core.domain.mark.review.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jake
 * @date : 2021-11-28
 */
@Getter
@NoArgsConstructor
public class ReviewListDto extends ReviewDto {

    public ReviewListDto(Review r) {
        super(r);
    }
}
