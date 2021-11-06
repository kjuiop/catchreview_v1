package io.gig.catchreview.core.exception;

/**
 * @author : Jake
 * @date : 2021-08-20
 */
public class RequiredParamNonException extends RuntimeException {

    public RequiredParamNonException() {
    }

    public RequiredParamNonException(String message) { super(message); }

    public RequiredParamNonException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredParamNonException(Throwable cause) {
        super(cause);
    }

    public RequiredParamNonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
