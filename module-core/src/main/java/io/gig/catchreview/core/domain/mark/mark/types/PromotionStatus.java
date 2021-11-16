package io.gig.catchreview.core.domain.mark.mark.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jake
 * @date : 2021-11-07
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PromotionStatus {

    PENDING("pending", "대기"),

    PROGRESS("progress", "진행중"),

    COMPLETE("complete", "마감");

    private String key;

    private String description;
}
