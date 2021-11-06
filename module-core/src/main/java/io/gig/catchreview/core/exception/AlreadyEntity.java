package io.gig.catchreview.core.exception;

/**
 * @author : Jake
 * @date : 2021-08-20
 */
public class AlreadyEntity extends RuntimeException {

    public AlreadyEntity() {
    }

    public AlreadyEntity(String message) { super(message); }

    public AlreadyEntity(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyEntity(Throwable cause) {
        super(cause);
    }

    public AlreadyEntity(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
