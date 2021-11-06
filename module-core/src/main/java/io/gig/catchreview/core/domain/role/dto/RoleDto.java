package io.gig.catchreview.core.domain.role.dto;

import io.gig.catchreview.core.domain.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jake
 * @date : 2021-08-21
 */
@Getter
@NoArgsConstructor
public class RoleDto {

    private String name;

    private String description;

    private int sortOrder;

    public RoleDto(Role r) {
        this.name = r.getName();
        this.description = r.getDescription();
        this.sortOrder = r.getSortOrder();
    }
}
