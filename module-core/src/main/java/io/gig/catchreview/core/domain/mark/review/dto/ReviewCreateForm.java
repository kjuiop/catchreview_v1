package io.gig.catchreview.core.domain.mark.review.dto;

import io.gig.catchreview.core.domain.mark.diary.dto.DiaryCreateForm;
import lombok.*;

import javax.validation.constraints.NotEmpty;

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

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String bannerImg;

    public ReviewCreateForm initCreateForm(Long markDetailId) {
        return ReviewCreateForm.builder()
                .markDetailId(markDetailId)
                .build();
    }

}
