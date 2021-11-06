package io.gig.catchreview.core.domain.user.member.dto;

import io.gig.catchreview.core.domain.user.validations.EqualPassword;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author : Jake
 * @date : 2021/10/10
 */
@Getter
@Setter
@Builder
@EqualPassword
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {

    @NotEmpty
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "최소 8자, 하나의 문자, 하나의 숫자 및 특수문자가 포함되어야 합니다.")
    @NotEmpty(message = "패스워드를 입력해주세요.")
    private String password;

    @NotEmpty(message = "패스워드 확인을 해주세요.")
    private String confirmPassword;

}
