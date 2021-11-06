package io.gig.catchreview.core.domain.user.member.dto;

import io.gig.catchreview.core.domain.user.UserStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Jake
 * @date : 2021-09-02
 */
@Getter
@Setter
public class MemberUpdateForm {

    private String username;

    private UserStatus status;
}
