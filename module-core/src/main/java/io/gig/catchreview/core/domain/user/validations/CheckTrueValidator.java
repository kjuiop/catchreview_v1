package io.gig.catchreview.core.domain.user.validations;

import io.gig.catchreview.core.domain.common.types.YnType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author : Jake
 * @date : 2021/08/12
 */
public class CheckTrueValidator implements ConstraintValidator<CheckTrue, YnType> {
    public void initialize(CheckTrue constraint) {
    }

    public boolean isValid(YnType obj, ConstraintValidatorContext context) {
        if (obj == null || obj == YnType.N) {
            context.disableDefaultConstraintViolation();;
            context.buildConstraintViolationWithTemplate(
                    "필수 선택값입니다."
            );
            return false;
        }
        return true;
    }
}
