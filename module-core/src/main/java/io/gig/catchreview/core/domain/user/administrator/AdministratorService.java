package io.gig.catchreview.core.domain.user.administrator;

import io.gig.catchreview.core.domain.role.Role;
import io.gig.catchreview.core.domain.role.RoleService;
import io.gig.catchreview.core.domain.user.UserService;
import io.gig.catchreview.core.domain.user.administrator.dto.*;
import io.gig.catchreview.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author : JAKE
 * @date : 2021/08/15
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdministratorService implements UserService<Administrator> {

    private final PasswordEncoder passwordEncoder;

    private final AdministratorRepository administratorRepository;
    private final AdministratorQueryRepository queryRepository;
    private final RoleService roleService;

    @Override
    public Administrator getUser(String username) {
        return queryRepository.findByUsernameAndRoles(username);
    }

    @Override
    public Administrator getUserByProviderEmail(String providerEmail) {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<AdministratorListDto> getAdminPageListBySearch(AdminSearchDto searchDto) {
        return queryRepository.getAdminPageListBySearch(searchDto);
    }

    @Transactional(readOnly = true)
    public AdministratorDetailDto getDetail(Long adminId) {
        Optional<AdministratorDetailDto> detailDto = queryRepository.getDetailDto(adminId);
        return detailDto.orElse(AdministratorDetailDto.emptyDto());
    }

    @Transactional
    public void initAdmin(String username, String password, String name, Set<Role> roles) {
        Administrator initAdministrator = Administrator.initAdministrator(username, password, name);
        for (Role role : roles) {
            AdministratorRole newRole = AdministratorRole.addAdministratorRole(initAdministrator, role);
            initAdministrator.addRole(newRole);
        }
        administratorRepository.save(initAdministrator);
    }

    @Override
    @Transactional
    public void loginSuccess(String username) {
        try {
            Administrator admin = getUser(username);
            admin.loginSuccess();
        } catch (NotFoundException ignore) {
        }
    }

    @Override
    @Transactional
    public void increasePasswordFailureCount(String username) {
        try {
            Administrator admin = getUser(username);
            admin.increasePasswordFailureCount();
        } catch (NotFoundException ignore) {

        }
    }

    @Override
    public boolean existsUsername(String username) {
        return queryRepository.existsByUsername(username);
    }

    @Transactional
    public Long create(@NotNull AdministratorCreateForm createForm) {
        Administrator newAdmin = Administrator.create(createForm, passwordEncoder.encode(createForm.getPassword()));
        List<Role> roles = roleService.findByRoleNamesIn(createForm.getRoleNames());
        newAdmin.createAdministratorRoles(roles);
        Administrator savedAdmin = administratorRepository.save(newAdmin);
        return savedAdmin.getId();
    }

    public Long update(@NotNull AdministratorUpdateForm updateForm) {
        Administrator administrator = getUser(updateForm.getUsername());
        if (!StringUtils.hasText(updateForm.getPassword())) {
            validPassword(administrator, updateForm.getPassword());
        }
        administrator.update(updateForm, passwordEncoder.encode(updateForm.getPassword()));
        List<Role> roles = roleService.findByRoleNamesIn(updateForm.getRoleNames());
        administrator.updateAdministratorRoles(roles);
        return administratorRepository.save(administrator).getId();
    }

    private void validPassword(Administrator administrator, String password) {
        if (administrator.passwordValid(password)) {
            throw new IllegalArgumentException("이전에 사용했던 비밀번호입니다.");
        }
    }
}
