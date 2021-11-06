package io.gig.catchreview.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author : Jake
 * @date : 2021/08/25
 *
 * 권한이 없는 접근을 하는 경우 Exception
 */
@Getter
public class ForbiddenException extends RuntimeException {

    private HttpStatus status = HttpStatus.FORBIDDEN;

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }

    protected ForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
