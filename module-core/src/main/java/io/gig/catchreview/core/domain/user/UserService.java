package io.gig.catchreview.core.domain.user;

/**
 * @author : Jake
 * @date : 2021/08/11
 */
public interface UserService<T> {

    T getUser(String username);

    T getUserByProviderEmail(String providerEmail);

    void loginSuccess(String username);

    void increasePasswordFailureCount(String username);

    boolean existsUsername(String username);
}
