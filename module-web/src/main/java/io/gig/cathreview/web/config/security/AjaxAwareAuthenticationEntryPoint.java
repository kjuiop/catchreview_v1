package io.gig.cathreview.web.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Jake
 * @date : 2021/08/26
 */
public class AjaxAwareAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public AjaxAwareAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String ajaxHeader = request.getHeader("X-Requested-With");

        boolean isAjaxRequest = XML_HTTP_REQUEST.equals(ajaxHeader);

        if (isAjaxRequest) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Ajax Request Denied");
        } else {
            super.commence(request, response, authException);
        }

    }
}
