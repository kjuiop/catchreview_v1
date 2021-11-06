package io.gig.catchreview.core.domain.user.member.dto;

import io.gig.catchreview.core.domain.user.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : Jake
 * @date : 2021-09-02
 */
@Getter
@NoArgsConstructor
public class MemberListDto extends MemberDto {

    private LocalDateTime joinedAt;

    private LocalDateTime lastLoginAt;

    public MemberListDto(Member m) {
        super(m);
        this.joinedAt = m.getJoinedAt();
        this.lastLoginAt = m.getLastLoginAt();
    }
}
