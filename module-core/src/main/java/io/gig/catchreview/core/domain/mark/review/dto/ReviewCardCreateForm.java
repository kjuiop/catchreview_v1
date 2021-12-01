package io.gig.catchreview.core.domain.mark.review.dto;

import lombok.*;

/**
 * @author : Jake
 * @date : 2021-12-01
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCardCreateForm {

    private String reviewImg;

    private String reviewText;
}
