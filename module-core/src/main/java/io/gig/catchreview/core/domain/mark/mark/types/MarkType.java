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
public enum MarkType {

    DIARY("diary", "일기"),

    STORE("store", "가게");

    private String key;

    private String description;
}
