package io.gig.catchreview.core.domain.mark.dto;

import io.gig.catchreview.core.domain.mark.Mark;
import io.gig.catchreview.core.domain.mark.MarkDetail;
import io.gig.catchreview.core.domain.mark.types.MarkType;
import lombok.*;
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
public class MarkDetailDto extends MarkDto {

    private MarkType markType;

    private String title;

    private String shortDescription;

    private String content;

    private String bannerImg;

    public MarkDetailDto(MarkDetail md) {
        super(md.getMark());

        this.markType           = md.getMarkType();
        this.title              = md.getTitle();
        this.shortDescription   = md.getShortDescription();
        this.content            = md.getContent();
        this.bannerImg          = md.getBannerImg();
    }
}
