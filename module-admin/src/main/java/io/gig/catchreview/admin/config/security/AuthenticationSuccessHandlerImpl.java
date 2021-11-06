package io.gig.catchreview.admin.config.security;

import io.gig.catchreview.core.domain.user.LoginUser;
import io.gig.catchreview.core.domain.user.administrator.AdministratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Jake
 * @date : 2021-08-20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler {

    private final AdministratorService administratorService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        String username = principal.getUsername();

        administratorService.loginSuccess(username);

        log.info(">> {} success", username);
    }
}
