package io.gig.catchreview.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author : Jake
 * @date : 2021/08/17
 *
 * 해당 Resource를 찾지 못하는 경우 Exception
 */
@Getter
public class NotFoundException extends RuntimeException {

    private HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
