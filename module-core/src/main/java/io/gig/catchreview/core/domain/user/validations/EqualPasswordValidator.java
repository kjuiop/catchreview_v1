package io.gig.catchreview.core.domain.user.validations;

import io.gig.catchreview.core.domain.user.member.SignUpForm;
import io.gig.catchreview.core.domain.user.member.dto.ChangePassword;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author : Jake
 * @date : 2021/08/12
 */
@Component
public class EqualPasswordValidator implements ConstraintValidator<EqualPassword, Object> {
    public void initialize(EqualPassword constraint) {
    }

    public boolean isValid(Object obj, ConstraintValidatorContext context) {

        String password = null;
        String confirmPassword = null;
        if (obj instanceof SignUpForm) {
            SignUpForm form = (SignUpForm) obj;
            password = form.getPassword();
            confirmPassword = form.getConfirmPassword();
        } else {
            ChangePassword form = (ChangePassword) obj;
            password = form.getPassword();
            confirmPassword = form.getConfirmPassword();
        }

        if (!password.equals(confirmPassword)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("비밀번호가 동일하지 않습니다.")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }


}
