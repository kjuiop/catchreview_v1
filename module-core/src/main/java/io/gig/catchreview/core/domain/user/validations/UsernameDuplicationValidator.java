package io.gig.catchreview.core.domain.user.validations;

import io.gig.catchreview.core.domain.user.UserService;
import io.gig.catchreview.core.domain.user.UserType;
import io.gig.catchreview.core.domain.user.administrator.AdministratorService;
import io.gig.catchreview.core.domain.user.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author : Jake
 * @date : 2021/08/12
 */
@Component
@RequiredArgsConstructor
public class UsernameDuplicationValidator implements ConstraintValidator<UniqueUsername, String> {

    private final MemberService memberService;
    private final AdministratorService administratorService;

    private UserService userService;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        if (constraintAnnotation.userType() == UserType.USER) {
            userService = memberService;
        } else if (constraintAnnotation.userType() == UserType.ADMIN) {
            userService = administratorService;
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        boolean isExists = userService.existsUsername(value);

        if (isExists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("이미 사용 중인 이메일입니다.")
                    .addConstraintViolation();
        }

        return !isExists;
    }
}
