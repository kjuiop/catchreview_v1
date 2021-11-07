package io.gig.cathreview.web.config;

import io.gig.catchreview.core.domain.menu.dto.MenuDto;
import io.gig.catchreview.core.domain.menu.types.MenuType;
import io.gig.catchreview.core.domain.user.LoginUser;
import io.gig.catchreview.core.domain.user.administrator.Administrator;
import io.gig.catchreview.core.domain.user.member.MemberService;
import io.gig.catchreview.core.domain.user.member.dto.MemberDetailDto;
import io.gig.catchreview.core.domain.user.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author : Jake
 * @date : 2021-11-06
 */
@Component
@RequiredArgsConstructor
public class WebInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            if (!principal.equals("anonymousUser") && mav != null) {
                LoginUser loginUser = (LoginUser) principal;
                MemberDetailDto member = memberService.getDetailByUsername(loginUser.getUsername());
                mav.addObject("loginUser", member);
            }
        }
    }



}
