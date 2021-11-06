package io.gig.catchreview.admin.config;

import io.gig.catchreview.admin.config.security.AdminSecurityService;
import io.gig.catchreview.core.domain.menu.MenuService;
import io.gig.catchreview.core.domain.menu.dto.MenuDto;
import io.gig.catchreview.core.domain.menu.types.MenuType;
import io.gig.catchreview.core.domain.user.administrator.Administrator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author : Jake
 * @date : 2021-08-21
 */
@Component
@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {

    private final AdminSecurityService adminSecurityService;
    private final MenuService menuService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) {

        if (mav != null) {
            Administrator loginUser = adminSecurityService.getLoginUser();
            if (loginUser != null) {
                List<MenuDto> menus = menuService.getMenuHierarchyByRoles(MenuType.AdminConsole, loginUser.getRoles());
                mav.addObject("menus", menus);
            }
        }
    }

}
