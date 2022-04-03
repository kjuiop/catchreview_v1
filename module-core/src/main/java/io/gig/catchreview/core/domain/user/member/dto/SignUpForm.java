package io.gig.catchreview.core.domain.user.member.dto;

import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.user.UserType;
import io.gig.catchreview.core.domain.user.validations.EqualPassword;
import io.gig.catchreview.core.domain.user.validations.UniqueUsername;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author : Jake
 * @date : 2021-11-01
 */
@Getter
@Setter
@Builder
//@EqualPassword
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {

    @UniqueUsername(userType = UserType.USER)
    @Pattern(regexp = "^[a-z0-9_+.-]+@([a-z0-9-]+\\.)+[a-z0-9]{2,4}$", message = "올바른 이메일 형식을 입력해주세요.")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "최소 8자, 하나의 문자, 하나의 숫자 및 특수문자가 포함되어야 합니다.")
    @NotEmpty(message = "패스워드를 입력해주세요.")
    private String password;

    private String confirmPassword;

    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "생년월일을 입력해주세요.")
    private String birth;

    private String gender;

    @Builder.Default
    private YnType privacyConfirm = YnType.N;

    @Builder.Default
    private YnType marketingConfirm = YnType.N;
}
