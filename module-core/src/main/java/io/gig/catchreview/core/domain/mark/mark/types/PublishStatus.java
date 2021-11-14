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
public enum PublishStatus {

    ME("Me", "로그인유저"),

    FRIENDS("Friends", "친구"),

    USER("User", "모든 유저");

    private String key;

    private String description;
}
