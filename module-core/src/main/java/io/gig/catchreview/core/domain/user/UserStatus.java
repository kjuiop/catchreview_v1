package io.gig.catchreview.core.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jake
 * @date : 2021/08/10
 */
@Getter
@NoArgsConstructor @AllArgsConstructor
public enum UserStatus {

    PENDING("Pending", "보류"),

    NORMAL("Normal", "정상"),

    WITHDRAW("Withdraw", "탈퇴"),

    INACTIVE("InActive", "비활성화");

    private String key;

    private String description;
}
