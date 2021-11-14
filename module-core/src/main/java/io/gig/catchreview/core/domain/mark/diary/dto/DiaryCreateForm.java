package io.gig.catchreview.core.domain.mark.diary.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

/**
 * @author : Jake
 * @date : 2021-11-14
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryCreateForm {

    private Long markDetailId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    public DiaryCreateForm initCreateForm(Long markDetailId) {
        return DiaryCreateForm.builder()
                .markDetailId(markDetailId)
                .build();
    }
}
