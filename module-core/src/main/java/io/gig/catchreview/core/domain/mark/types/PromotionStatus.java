package io.gig.catchreview.core.domain.mark.types;

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

    PENDING("Pending", "대기"),

    PROGRESS("Progress", "진행중"),

    COMPLETE("Complete", "마감");

    private String key;

    private String description;
}
