package io.gig.catchreview.core.domain.user.validations;

import io.gig.catchreview.core.domain.user.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author : Jake
 * @date : 2021/08/17
 */
@Component
@RequiredArgsConstructor
public class NicknameDuplicationValidator implements ConstraintValidator<UniqueNickname, String> {

    private final MemberService memberService;

    @Override
    public void initialize(UniqueNickname constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        boolean isExists = memberService.existsNickname(value);

        if (isExists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("이미 사용 중인 닉네임입니다.")
                    .addConstraintViolation();
        }

        return !isExists;
    }
}
