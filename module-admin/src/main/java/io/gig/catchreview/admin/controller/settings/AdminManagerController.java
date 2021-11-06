package io.gig.catchreview.admin.controller.settings;

import io.gig.catchreview.core.domain.role.RoleService;
import io.gig.catchreview.core.domain.role.dto.RoleDto;
import io.gig.catchreview.core.domain.user.administrator.AdministratorService;
import io.gig.catchreview.core.domain.user.administrator.dto.AdminSearchDto;
import io.gig.catchreview.core.domain.user.administrator.dto.AdministratorCreateForm;
import io.gig.catchreview.core.domain.user.administrator.dto.AdministratorDetailDto;
import io.gig.catchreview.core.domain.user.administrator.dto.AdministratorUpdateForm;
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
 * @date : 2021-08-26
 */
@Controller
@RequestMapping("settings/admin-manager")
@Slf4j
@RequiredArgsConstructor
public class AdminManagerController {

    private final AdministratorService administratorService;
    private final RoleService roleService;

    @GetMapping
    public String index(AdminSearchDto searchDto, Model model) {
        model.addAttribute("pages", administratorService.getAdminPageListBySearch(searchDto));
        model.addAttribute("condition", searchDto);
        return "settings/administrator/list";
    }

    @GetMapping("new")
    public String register(Model model) {
        List<RoleDto> roles = roleService.getAllRoles();
        AdministratorDetailDto dto = AdministratorDetailDto.emptyDto();

        model.addAttribute("roles", roles);
        model.addAttribute("dto", dto);

        return "settings/administrator/editor";
    }

    @GetMapping("{adminId}/edit")
    public String editForm(@PathVariable(name = "adminId") Long adminId, Model model) {
        List<RoleDto> roles = roleService.getAllRoles();
        AdministratorDetailDto dto = administratorService.getDetail(adminId);

        model.addAttribute("roles", roles);
        model.addAttribute("dto", dto);

        return "settings/administrator/editor";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity save(@Valid @RequestBody AdministratorCreateForm createForm,
                               Errors errors,
                               RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        Long adminId = administratorService.create(createForm);
        return ResponseEntity.ok().body(adminId);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity update(@Valid @RequestBody AdministratorUpdateForm updateForm,
                               Errors errors,
                               RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        Long adminId = administratorService.update(updateForm);
        return ResponseEntity.ok().body(adminId);
    }

    @GetMapping("check-duplicate/username/{value}")
    @ResponseBody
    public ResponseEntity checkDuplicateData(
            @PathVariable(value = "value") String value) {

        boolean isDuplicate = administratorService.existsUsername(value);
        return ResponseEntity.ok().body(isDuplicate);
    }
}
