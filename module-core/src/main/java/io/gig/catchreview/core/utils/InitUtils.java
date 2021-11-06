package io.gig.catchreview.core.utils;

import io.gig.catchreview.core.domain.menu.Menu;
import io.gig.catchreview.core.domain.menu.MenuRepository;
import io.gig.catchreview.core.domain.menu.MenuService;
import io.gig.catchreview.core.domain.role.Role;
import io.gig.catchreview.core.domain.role.RoleRepository;
import io.gig.catchreview.core.domain.role.RoleService;
import io.gig.catchreview.core.domain.user.administrator.AdministratorRepository;
import io.gig.catchreview.core.domain.user.administrator.AdministratorService;
import io.gig.catchreview.core.exception.AlreadyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Jake
 * @date : 2021-11-06
 */
@Component
@RequiredArgsConstructor
public class InitUtils {

    private final AdministratorRepository administratorRepository;
    private final RoleRepository roleRepository;
    private final MenuRepository menuRepository;

    private final RoleService roleService;
    private final AdministratorService administratorService;
    private final MenuService menuService;

    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = {AlreadyEntity.class})
    public void initData() {

        validationAlreadyEntity();

        roleService.initRole("ROLE_SUPER_ADMIN", "슈퍼관리자", 0);
        roleService.initRole("ROLE_ADMIN", "관리자", 1);
        roleService.initRole("ROLE_USER", "사용자", 2);
        roleService.initRole("ROLE_ANONYMOUS", "익명사용자", 3);

        Role superAdminRole = roleService.findByRoleName("ROLE_SUPER_ADMIN");
        Role adminRole = roleService.findByRoleName("ROLE_ADMIN");

        Set<Role> roles = new HashSet<>();
        roles.add(superAdminRole);
        roles.add(adminRole);

        administratorService.initAdmin("admin@catchreview.io", passwordEncoder.encode("gig123$"), "초기관리자", roles);
        menuService.initMenu("Home", "/", "fa fa-home", 0, roles);
        menuService.initMenu("회원관리", "/members", "fa fa-users", 0, roles);
        Menu settingMenu = menuService.initMenu("설정", "/settings", "fa fa-gear", 99, roles);
        menuService.initChildMenu("메뉴관리", "/settings/menu-manager", "fa fa-circle-o", 1, roles, settingMenu);
        menuService.initChildMenu("관리자관리", "/settings/admin-manager", "fa fa-circle-o", 2, roles, settingMenu);
    }

    private void validationAlreadyEntity() {
        if (administratorRepository.count() > 0) throw new AlreadyEntity(">>> Already Exist Data");
        if (roleRepository.count() > 0) throw new AlreadyEntity(">>> Already Exist Data");
        if (menuRepository.count() > 0) throw new AlreadyEntity(">>> Already Exist Data");
    }
}
