package io.gig.catchreview.core.domain.user.member;

import io.gig.catchreview.core.exception.NotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : Jake
 * @date : 2021/08/31
 */
@Getter
@RequiredArgsConstructor
public enum LoginType {

    NORMAL("normal"),
    GOOGLE("google"),
    FACEBOOK("facebook"),
    NAVER("naver"),
    KAKAO("kakao");

    private final String provider;

    public static LoginType getType(String provider) {
        LoginType[] values = LoginType.values();
        for (LoginType value : values) {
            if (value.getProvider().toUpperCase().equals(provider.toUpperCase())) {
                return value;
            }
        }
        throw new NotFoundException("해당 OAuth 타입을 찾을 수 없습니다.");
    }

    public static boolean isNormal(String requestProvider) {
        return LoginType.NORMAL.getProvider().equals(requestProvider);
    }

    public static boolean isGoogle(String requestProvider) {
        return LoginType.GOOGLE.getProvider().equals(requestProvider);
    }

    public static boolean isFacebook(String requestProvider) {
        return LoginType.FACEBOOK.getProvider().equals(requestProvider);
    }

    public static boolean isNaver(String requestProvider) {
        return LoginType.NAVER.getProvider().equals(requestProvider);
    }

    public static boolean isKakao(String requestProvider) {
        return LoginType.KAKAO.getProvider().equals(requestProvider);
    }
}
