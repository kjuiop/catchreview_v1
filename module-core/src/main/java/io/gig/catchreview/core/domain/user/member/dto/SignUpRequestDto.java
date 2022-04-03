package io.gig.catchreview.core.domain.user.member.dto;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author : JAKE
 * @date : 2022/04/03
 */
@Getter
@Builder
public class SignUpRequestDto {

    @NotBlank
    @Pattern(regexp = "^[a-z0-9_+.-]+@([a-z0-9-]+\\.)+[a-z0-9]{2,4}$", message = "올바른 이메일 형식을 입력해주세요.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "최소 8자, 하나의 문자, 하나의 숫자 및 특수문자가 포함되어야 합니다.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Length(min = 11, max = 12)
    private String phone;

    @NotBlank(message = "생년월일을 입력해주세요.")
    private String birth;

    @NotEmpty
    private Boolean useOfTerm;

    private Boolean marketingAgreement;
}
