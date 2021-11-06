package io.gig.catchreview.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author : Jake
 * @date : 2021/09/09
 *
 * 로그인은 되어있지만 해당 자원에 접근 권한이 없는 경우
 *
 */
@Getter
public class UnauthorizedException extends RuntimeException {

    private HttpStatus status = HttpStatus.UNAUTHORIZED;
    private String responseMessage;

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, String responseMessage) {
        super(message);
        this.responseMessage = responseMessage;
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    protected UnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
