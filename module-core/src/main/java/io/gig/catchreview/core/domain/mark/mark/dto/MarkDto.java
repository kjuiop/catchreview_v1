package io.gig.catchreview.core.domain.mark.mark.dto;

import io.gig.catchreview.core.domain.mark.mark.Mark;
import io.gig.catchreview.core.domain.mark.mark.types.ApplyStatus;
import io.gig.catchreview.core.domain.mark.mark.types.PromotionStatus;
import io.gig.catchreview.core.domain.mark.mark.types.PublishStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author : Jake
 * @date : 2021-11-14
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class MarkDto {

    private Long id;

    private String title;

    private int point;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private ApplyStatus applyStatus;

    private PromotionStatus promotionStatus;

    private PublishStatus publishStatus;

    public MarkDto(Mark m) {
        this.id                 = m.getId();
        this.point              = m.getPoint();
        this.startDateTime      = m.getStartDateTime();
        this.endDateTime        = m.getEndDateTime();
        this.applyStatus        = m.getApplyStatus();
        this.promotionStatus    = m.getPromotionStatus();
        this.publishStatus      = m.getPublishStatus();
    }
}
