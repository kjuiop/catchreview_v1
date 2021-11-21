package io.gig.catchreview.core.domain.mark.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

/**
 * @author : Jake
 * @date : 2021-11-22
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiarySearchDto {

    private int page = 0;

    private int size = 5;

    public PageRequest getPageRequest() {
        return PageRequest.of(this.page, this.size);
    }

}
