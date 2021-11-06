package io.gig.catchreview.core.domain.email;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : Jake
 * @date : 2021/10/07
 */
@Getter
@RequiredArgsConstructor
public enum EmailInfo {
    // [CatchReview]

    WELCOME("welcome", "[CatchReview] 가입을 환영합니다 🎉"),

    VERIFY("verify", "[CatchReview] 이메일 인증을 완료해주세요."),

    REVERIFY("reverify", "[CatchReview] 이메일 재인증을 완료해주세요."),

    FORGOT_PASSWORD("forgot_password", "[CatchReview] 패스워드 찾기 메일입니다. 🔍");

    private final String template;

    private final String title;
}
