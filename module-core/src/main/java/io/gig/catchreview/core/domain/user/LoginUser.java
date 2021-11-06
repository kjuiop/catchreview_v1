package io.gig.catchreview.core.domain.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @author : Jake
 * @date : 2021/08/11
 */
@Getter
public class LoginUser extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private AbstractUser loginUser;
    @Setter
    private Map<String, Object> attributes;
    @Setter
    private boolean needsModified = false;

    public LoginUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, AbstractUser loginUser) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.loginUser = loginUser;
    }

    public LoginUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, AbstractUser loginUser, Map<String, Object> attributes) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.loginUser = loginUser;
        this.attributes = attributes;
    }

    public LoginUser(String username, String password, AbstractUser loginUser, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes) {
        super(username, password, authorities);
        this.attributes = attributes;
        this.loginUser = loginUser;
    }

}
