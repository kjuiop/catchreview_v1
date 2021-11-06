package io.gig.catchreview.core.domain.user.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author : Jake
 * @date : 2021/08/12
 */
@Documented
@Constraint(validatedBy = CheckTrueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckTrue {

    String message() default "필수선택 값입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
