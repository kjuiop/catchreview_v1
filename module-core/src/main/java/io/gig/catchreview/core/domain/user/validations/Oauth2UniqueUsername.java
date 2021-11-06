package io.gig.catchreview.core.domain.user.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author : Jake
 * @date : 2021/08/12
 */
@Documented
@Constraint(validatedBy = Oauth2UsernameDuplicationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Oauth2UniqueUsername {

    String message() default "이미 가입된 이메일입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
