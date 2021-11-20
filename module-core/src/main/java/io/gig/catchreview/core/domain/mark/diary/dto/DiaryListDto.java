package io.gig.catchreview.core.domain.mark.diary.dto;

import io.gig.catchreview.core.domain.mark.diary.Diary;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jake
 * @date : 2021-11-16
 */
@Getter
@NoArgsConstructor
public class DiaryListDto extends DiaryDto {

    public DiaryListDto(Diary d) {
        super(d);
    }
}
