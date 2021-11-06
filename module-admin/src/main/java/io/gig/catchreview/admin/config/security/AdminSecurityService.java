package io.gig.catchreview.admin.config.security;

import io.gig.catchreview.core.domain.user.LoginUser;
import io.gig.catchreview.core.domain.user.SecurityService;
import io.gig.catchreview.core.domain.user.administrator.Administrator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author : Jake
 * @date : 2021-08-21
 */
@Service
public class AdminSecurityService implements SecurityService<Administrator> {

    @Override
    public Administrator getLoginUser() {
        if (SecurityContextHolder.getContext() != null) {
            if (SecurityContextHolder.getContext().getAuthentication() == null) return null;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof LoginUser) {
                return (Administrator) ((LoginUser) principal).getLoginUser();
            } else {
                return null;
            }
        }
        return null;
    }
}
