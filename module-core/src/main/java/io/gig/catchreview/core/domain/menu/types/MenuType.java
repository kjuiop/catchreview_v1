package io.gig.catchreview.core.domain.menu.types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : Jake
 * @date : 2021-11-06
 */
@Getter
@RequiredArgsConstructor
public enum MenuType {

    AdminConsole("admin", "관리자"),
    Web("front", "프론트");

    final private String type;
    final private String description;
}
