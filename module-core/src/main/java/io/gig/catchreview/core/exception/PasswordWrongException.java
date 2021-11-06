package io.gig.catchreview.core.exception;

import lombok.Getter;

/**
 * @author : Jake
 * @date : 2021-09-12
 */
@Getter
public class PasswordWrongException extends RuntimeException {

    public PasswordWrongException(String message) {
        super(message);
    }

    public PasswordWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordWrongException(Throwable cause) {
        super(cause);
    }

    protected PasswordWrongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
