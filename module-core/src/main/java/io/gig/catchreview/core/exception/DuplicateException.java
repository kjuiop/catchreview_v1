package io.gig.catchreview.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author : Jake
 * @date : 2021/08/31
 *
 * 해당 객체가 중복인 경우
 *
 */
@Getter
public class DuplicateException extends RuntimeException {

    private HttpStatus status = HttpStatus.CONFLICT;

    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateException(Throwable cause) {
        super(cause);
    }

    protected DuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
