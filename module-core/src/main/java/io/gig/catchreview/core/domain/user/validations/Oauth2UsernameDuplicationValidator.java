package io.gig.catchreview.core.domain.user.validations;

import io.gig.catchreview.core.domain.user.LoginUser;
import io.gig.catchreview.core.domain.user.member.Member;
import io.gig.catchreview.core.domain.user.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * @author : Jake
 * @date : 2021/08/12
 */
@Component
@RequiredArgsConstructor
public class Oauth2UsernameDuplicationValidator implements ConstraintValidator<Oauth2UniqueUsername, String> {

    private final MemberService memberService;

    @Override
    public void initialize(Oauth2UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Optional<Member> optMember = memberService.getUserForOauth(value);
        LoginUser principal = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (optMember.isEmpty()) {  // 비어있으면 없으니 ok
            return true;
        }

        Member member = optMember.get();

        if (member.getId().equals(principal.getLoginUser().getId())) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("이미 사용 중인 이메일입니다.")
                .addConstraintViolation();

        return false;
    }
}
