package io.gig.catchreview.core.domain.mark.review.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Jake
 * @date : 2021-11-28
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateForm {

    private Long markDetailId;

    private String title;

    @NotEmpty
    private String content;

    private String bannerImg;

    private List<ReviewCardCreateForm> reviewCardList = new ArrayList<>();

    public ReviewCreateForm initCreateForm(Long markDetailId) {
        return ReviewCreateForm.builder()
                .markDetailId(markDetailId)
                .build();
    }

}
