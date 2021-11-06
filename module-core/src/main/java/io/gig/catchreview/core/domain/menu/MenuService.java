package io.gig.catchreview.core.domain.menu;

import io.gig.catchreview.core.domain.menu.dto.MenuCreateForm;
import io.gig.catchreview.core.domain.menu.dto.MenuDto;
import io.gig.catchreview.core.domain.menu.dto.MenuUpdateForm;
import io.gig.catchreview.core.domain.menu.types.MenuType;
import io.gig.catchreview.core.domain.role.Role;
import io.gig.catchreview.core.domain.role.RoleService;
import io.gig.catchreview.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author : Jake
 * @date : 2021-08-20
 */
@Service
@RequiredArgsConstructor
public class MenuService {

    private final RoleService roleService;
    private final MenuRepository menuRepository;
    private final MenuQueryRepository queryRepository;

    @Transactional
    public Menu initMenu(String name, String url, String iconClass, int sortOrder, Set<Role> roles) {
        Menu newMenu = Menu.initMenu(name, url, iconClass, sortOrder, roles);
        return menuRepository.save(newMenu);
    }

    @Transactional
    public void initChildMenu(String name, String url, String iconClass, int sortOrder, Set<Role> roles, Menu parentMenu) {
        Menu newMenu = Menu.initMenu(name, url, iconClass, sortOrder, roles);
        newMenu.addParent(parentMenu);
        menuRepository.save(newMenu);
    }

    @Transactional(readOnly = true)
    public List<MenuDto> getMenuHierarchyByRoles(MenuType menuType, Set<Role> roles) {
        List<Menu> topMenus = queryRepository.getMenuHierarchyByRoles(menuType, roles);
        List<MenuDto> hierarchy = new ArrayList<>();
        for (Menu m : topMenus) {
            hierarchy.add(new MenuDto(m, true));
        }
        return hierarchy;
    }

    @Transactional(readOnly = true)
    public List<MenuDto> getAllMenuHierarchy(MenuType menuType) {
        List<Menu> topMenus = queryRepository.getAllMenuHierarchy(menuType);
        List<MenuDto> hierarchy = new ArrayList<>();
        for (Menu m : topMenus) {
            hierarchy.add(new MenuDto(m, true));
        }
        return hierarchy;
    }

    @Transactional(readOnly = true)
    public MenuDto getMenuDtoIncludeParent(Long id) {
        Optional<Menu> optMenu = menuRepository.findById(id);
        if (optMenu.isEmpty())
            throw new NotFoundException(">>> Menu not found");

        Menu menu = optMenu.get();
        return MenuDto.includeParent(menu);
    }

    @Transactional(readOnly = true)
    public Menu getMenu(Long id) {
        Optional<Menu> foundMenu = menuRepository.findById(id);
        if (foundMenu.isEmpty()) {
            throw new NotFoundException(">>> menu not found");
        }
        return foundMenu.get();
    }

    @Transactional
    public Long create(@NotNull MenuCreateForm dto) {
        Menu newMenu = Menu.create(dto);
        if (dto.existParentId()) {
            Menu parent = getMenu(dto.getParentId());
            newMenu.addParent(parent);
        }

        setMenuRole(newMenu, dto.getRoleNames());
        return menuRepository.save(newMenu).getId();
    }

    @Transactional
    public Long update(@NotNull MenuUpdateForm dto) {
        Menu foundMenu = getMenu(dto.getId());
        foundMenu.update(dto);
        setMenuRole(foundMenu, dto.getRoleNames());
        return menuRepository.save(foundMenu).getId();
    }

    private void setMenuRole(Menu menu, List<String> roleNames) {
        menu.getRoles().clear();
        List<Role> roles = roleService.findByRoleNamesIn(roleNames);
        menu.getRoles().addAll(roles);
    }
}
