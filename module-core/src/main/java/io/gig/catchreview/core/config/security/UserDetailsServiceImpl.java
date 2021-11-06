package io.gig.catchreview.core.config.security;

import io.gig.catchreview.core.domain.user.AbstractUser;
import io.gig.catchreview.core.domain.user.LoginUser;
import io.gig.catchreview.core.domain.user.UserService;
import io.gig.catchreview.core.domain.user.UserType;
import io.gig.catchreview.core.domain.user.administrator.AdministratorService;
import io.gig.catchreview.core.domain.user.member.MemberService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Jake
 * @date : 2021-10-30
 */
@Service
@Slf4j
@NoArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;
    private UserType userType;

    public UserDetailsServiceImpl(MemberService memberService) {
        userService = memberService;
        userType = UserType.USER;
    }

    public UserDetailsServiceImpl(AdministratorService administratorService) {
        userService = administratorService;
        userType = UserType.ADMIN;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AbstractUser user;

        user = (AbstractUser) userService.getUser(username);

        // Role
        Set<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toSet());

        boolean loginEnabled = true;
        boolean accountNonExpired = true;
        boolean credentialNonExpired = true;
        boolean accountNonLocked = true;

        if (user.getPasswordFailureCount() >= 5) {
            accountNonLocked = false;
        }

        if (!user.isNormal()) {
            loginEnabled = false;
        }

        if (!user.isValidEmail()) {
            credentialNonExpired = false;
        }

        return new LoginUser(user.getUsername(), user.getPassword(), loginEnabled, accountNonExpired, credentialNonExpired, accountNonLocked, authorities, user);
    }
}
