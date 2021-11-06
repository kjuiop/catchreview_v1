package io.gig.catchreview.core.domain.user.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author : Jake
 * @date : 2021/08/12
 */
@Documented
@Constraint(validatedBy = EqualPasswordValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EqualPassword {

    String message() default "패스워드가 일치하지 않습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
