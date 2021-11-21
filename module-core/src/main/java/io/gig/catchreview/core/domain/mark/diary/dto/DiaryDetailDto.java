package io.gig.catchreview.core.domain.mark.diary.dto;

import io.gig.catchreview.core.domain.mark.diary.Diary;
import io.gig.catchreview.core.domain.mark.mark.MarkDetail;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkDto;
import io.gig.catchreview.core.domain.mark.mark.types.MarkType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author : Jake
 * @date : 2021-11-21
 */
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDetailDto extends DiaryDto {

    private String tag;

    public DiaryDetailDto(Diary d) {
        super(d);
    }
}
