package io.gig.catchreview.core.domain.user.member.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

/**
 * @author : Jake
 * @date : 2021-09-06
 */
@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class LeaveForm {


    @NotEmpty(message = "패스워드를 입력해주세요.")
    private String password;
}
