package io.gig.catchreview.core.domain.role;

import io.gig.catchreview.core.domain.role.dto.RoleDto;
import io.gig.catchreview.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : Jake
 * @date : 2021-08-18
 */
@Service
@RequiredArgsConstructor
public class RoleService {

    private final String prefix = "ROLE_";
    private final RoleRepository roleRepository;

    @Transactional
    public Role initRole(String name, String description, int sortOrder) {
        validationRoleName(name);
        Role newRole = Role.createRole(name, description, sortOrder);
        return roleRepository.save(newRole);
    }

    private void validationRoleName(String name) {
        if (!StringUtils.hasText(name)) { throw new IllegalArgumentException(""); }
        if (existsRoleName(name))  { throw new RuntimeException(); }
        if (!checkedPrefixRoleName(name)) { throw new RuntimeException(); }
    }

    public boolean existsRoleName(String roleName) {
        return roleRepository.existsByName(roleName);
    }

    private boolean checkedPrefixRoleName(String name) {
        return name.toUpperCase().startsWith(prefix);
    }

    public Role findByRoleName(String roleName) {
        Optional<Role> findRole = roleRepository.findById(roleName);
        if (findRole.isPresent()) {
            return findRole.get();
        } else {
            throw new NotFoundException(">>> Role Not Found");
        }
    }

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAllByOrderBySortOrderAsc().stream().map(RoleDto::new).collect(Collectors.toList());
    }

    public List<Role> findByRoleNamesIn(List<String> roleNames) {
        return roleRepository.findAllByNameIn(roleNames);
    }
}
