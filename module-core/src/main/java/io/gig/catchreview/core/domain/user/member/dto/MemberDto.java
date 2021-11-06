package io.gig.catchreview.core.domain.user.member.dto;

import io.gig.catchreview.core.domain.user.UserStatus;
import io.gig.catchreview.core.domain.user.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author : Jake
 * @date : 2021/08/24
 */
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;

    private String username;

    private String nickname;

    private UserStatus status;

    private String profile;

    private String bio;

    public MemberDto(Member m) {
        this.id = m.getId();
        this.username = m.getUsername();
        this.nickname = m.getNickname();
        this.status = m.getStatus();
    }

    public static MemberDto toDto(Member m) {
        return MemberDto.builder()
                .id(m.getId())
                .nickname(m.getNickname())
                .build();
    }

}
