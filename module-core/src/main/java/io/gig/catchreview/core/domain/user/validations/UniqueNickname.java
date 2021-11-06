package io.gig.catchreview.core.domain.user.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author : Jake
 * @date : 2021/08/17
 */
@Documented
@Constraint(validatedBy = NicknameDuplicationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueNickname {

    String message() default "이미 존재하는 닉네임 입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
