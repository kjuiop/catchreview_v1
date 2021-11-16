package io.gig.catchreview.core.domain.mark.diary.dto;

import io.gig.catchreview.core.domain.mark.diary.Diary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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

    public DiaryDto(Diary d) {
        this.diaryId        = d.getId();
        this.title          = d.getTitle();
        this.content        = d.getContent();
    }
}
