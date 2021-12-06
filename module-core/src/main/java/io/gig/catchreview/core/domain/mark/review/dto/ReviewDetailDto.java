package io.gig.catchreview.core.domain.mark.review.dto;

import io.gig.catchreview.core.domain.mark.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Jake
 * @date : 2021-11-21
 */
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDetailDto extends ReviewDto {

    private String nickname;

    private List<ReviewCardDto> cards = new ArrayList<>();

    public ReviewDetailDto(Review r) {
        super(r);
        this.nickname = r.getCreatedByMember().getNickname();
        this.cards = r.getReviewCardList().stream().map(ReviewCardDto::new).collect(Collectors.toList());
    }
}
