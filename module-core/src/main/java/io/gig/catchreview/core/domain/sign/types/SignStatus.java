package io.gig.catchreview.core.domain.sign.types;

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
public enum SignStatus {

    APPLY("Apply", "신청"),

    PENDING("Pending", "대기"),

    APPROVE("Approve", "승인"),

    REJECT("Reject", "반려");

    private String key;

    private String description;
}
