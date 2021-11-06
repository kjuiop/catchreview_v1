package io.gig.catchreview.core.domain.user.validations;

import io.gig.catchreview.core.domain.user.UserType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author : Jake
 * @date : 2021/08/12
 */
@Documented
@Constraint(validatedBy = UsernameDuplicationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {

    String message() default "이미 가입된 이메일입니다.";

    UserType userType() default UserType.USER;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
