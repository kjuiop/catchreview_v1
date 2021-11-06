package io.gig.catchreview.core.domain.user.administrator.dto;

import io.gig.catchreview.core.domain.user.UserStatus;
import io.gig.catchreview.core.domain.user.UserType;
import io.gig.catchreview.core.domain.user.validations.UniqueUsername;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author : Jake
 * @date : 2021-08-26
 */
@Getter
@Setter
public class AdministratorCreateForm {

    @UniqueUsername(userType = UserType.ADMIN)
    @Email(message = "올바른 Email을 입력해주세요.")
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String username;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @NotEmpty(message = "패스워드를 입력해주세요.")
    private String password;

    @NotEmpty(message = "패스워드를 확인해주세요.")
    private String confirmPassword;

    private UserStatus status;

    @NotEmpty(message = "역할을 활성화해주세요.")
    private List<String> roleNames;
}
