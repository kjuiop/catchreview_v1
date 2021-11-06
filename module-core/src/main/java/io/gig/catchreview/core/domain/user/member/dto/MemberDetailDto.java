package io.gig.catchreview.core.domain.user.member.dto;

import io.gig.catchreview.core.domain.user.member.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author : Jake
 * @date : 2021-09-02
 */
@SuperBuilder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailDto extends MemberDto {

    private String profileTitle;

    private String link;

    private String occupation;

    private LocalDateTime lastLoginAt;

    private LocalDateTime joinedAt;

    private static final MemberDetailDto EMPTY;

    static {
        EMPTY = MemberDetailDto.builder()
                .empty(true)
                .build();
    }

    @Builder.Default
    private boolean empty = false;

    public static MemberDetailDto emptyDto() {
        return EMPTY;
    }

    public MemberDetailDto(Member m) {
        super(m);
        this.lastLoginAt = m.getLastLoginAt();
        this.joinedAt = m.getJoinedAt();
    }

}
