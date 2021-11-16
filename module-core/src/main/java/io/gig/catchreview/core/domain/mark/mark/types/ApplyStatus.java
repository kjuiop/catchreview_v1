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
public enum ApplyStatus {

    APPLY("apply", "신청"),

    PENDING("pending", "대기"),

    APPROVE("approve", "승인"),

    REJECT("reject", "반려");

    private String key;

    private String description;
}
