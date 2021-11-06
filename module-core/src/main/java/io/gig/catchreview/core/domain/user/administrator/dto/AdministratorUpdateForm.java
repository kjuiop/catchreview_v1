package io.gig.catchreview.core.domain.user.administrator.dto;

import io.gig.catchreview.core.domain.user.UserStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author : Jake
 * @date : 2021-08-26
 */
@Getter
@Setter
public class AdministratorUpdateForm {

    private Long adminId;

    private String username;

    private String password;

    private String confirmPassword;

    private UserStatus status;

    @NotEmpty(message = "역할을 활성화해주세요.")
    private List<String> roleNames;
}
