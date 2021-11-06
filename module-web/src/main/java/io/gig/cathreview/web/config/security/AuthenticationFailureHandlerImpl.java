package io.gig.cathreview.web.config.security;

import io.gig.catchreview.core.domain.user.member.MemberService;
import io.gig.catchreview.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author : Jake
 * @date : 2021/08/12
 */
@Component
@RequiredArgsConstructor
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    private static final String DEFAULT_URL = "/login?error=500";
    private final MemberService memberService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = null;
        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            errorMessage = "계정 정보가 잘못되었습니다.";
            try {
                String username = request.getParameter("username");
                memberService.increasePasswordFailureCount(username);
            } catch (NotFoundException ignore) {}
        } else if (exception instanceof DisabledException) {
            errorMessage = "이메일 인증 후 로그인해주세요.";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "인증이 만료되었습니다.";
        } else  if (exception instanceof LockedException) {
            errorMessage = "패스워드 5회 이상 틀려 계정이 잠겼습니다.";
        } else {
            errorMessage = "알 수 없는 문제가 발생하였습니다.";
        }

        request.setAttribute("authError", errorMessage);
        request.getRequestDispatcher(DEFAULT_URL).forward(request, response);
    }

}
