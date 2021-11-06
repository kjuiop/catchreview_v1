package io.gig.catchreview.admin.controller.settings;

import io.gig.catchreview.core.domain.menu.MenuService;
import io.gig.catchreview.core.domain.menu.dto.MenuCreateForm;
import io.gig.catchreview.core.domain.menu.dto.MenuDto;
import io.gig.catchreview.core.domain.menu.dto.MenuUpdateForm;
import io.gig.catchreview.core.domain.menu.types.MenuType;
import io.gig.catchreview.core.domain.role.RoleService;
import io.gig.catchreview.core.domain.role.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : Jake
 * @date : 2021-08-22
 */
@Controller
@RequestMapping("settings/menu-manager")
@Slf4j
@RequiredArgsConstructor
public class MenuManagerController {

    static final String ROOT = "settings/menu-manager";

    private final MenuService menuService;
    private final RoleService roleService;

    @GetMapping
    public String index(Model model) {

        List<MenuDto> acMenus = menuService.getAllMenuHierarchy(MenuType.AdminConsole);
        List<RoleDto> roles = roleService.getAllRoles();

        model.addAttribute("acMenus", acMenus);
        model.addAttribute("roles", roles);

        return "settings/menu/menu-manager";
    }

    @GetMapping("menu/{id}")
    public ResponseEntity<MenuDto> getAjaxMenu(@PathVariable(name = "id") Long id) {
        MenuDto dto = menuService.getMenuDtoIncludeParent(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity save(@Valid @RequestBody MenuCreateForm createForm,
                                Errors errors,
                                RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        Long menuId = menuService.create(createForm);
        return ResponseEntity.ok().body(menuId);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity update(@Valid @RequestBody MenuUpdateForm updateForm,
                               Errors errors,
                               RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        Long menuId = menuService.update(updateForm);
        return ResponseEntity.ok().body(menuId);
    }

}
