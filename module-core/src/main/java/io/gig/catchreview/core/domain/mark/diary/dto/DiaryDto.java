package io.gig.catchreview.core.domain.mark.diary.dto;

import io.gig.catchreview.core.domain.mark.diary.Diary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author : Jake
 * @date : 2021-11-16
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class DiaryDto {

    private Long diaryId;

    private String title;

    private String content;

    private Long markDetailId;

    private LocalDateTime createdAt;

    public DiaryDto(Diary d) {
        this.diaryId        = d.getId();
        this.title          = d.getTitle();
        this.content        = d.getContent();
        this.markDetailId   = d.getMarkDetail().getId();
        this.createdAt      = d.getCreatedAt();
    }
}
