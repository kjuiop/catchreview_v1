package io.gig.catchreview.core.domain.user;

/**
 * @author : Jake
 * @date : 2021-08-21
 * @param <U> LoginUser Model
 */
public interface SecurityService<U> {

    U getLoginUser();
}
