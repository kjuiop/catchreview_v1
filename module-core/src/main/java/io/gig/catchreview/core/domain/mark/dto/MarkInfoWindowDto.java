package io.gig.catchreview.core.domain.mark.dto;

import io.gig.catchreview.core.domain.mark.MarkDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author : Jake
 * @date : 2021-11-14
 */
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarkInfoWindowDto {

    private Long markDetailId;

    private String title;

    public MarkInfoWindowDto(MarkDetail md) {
        this.markDetailId   = md.getId();
        this.title          = md.getTitle();
    }
}
