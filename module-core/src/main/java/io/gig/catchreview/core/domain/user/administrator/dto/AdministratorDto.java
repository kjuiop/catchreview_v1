package io.gig.catchreview.core.domain.user.administrator.dto;

import io.gig.catchreview.core.domain.user.UserStatus;
import io.gig.catchreview.core.domain.user.administrator.Administrator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author : Jake
 * @date : 2021-08-26
 */
@SuperBuilder
@Getter @Setter
@NoArgsConstructor
public class AdministratorDto {

    private Long adminId;

    private String username;

    private String name;

    private UserStatus status;

    private LocalDateTime lastLoginAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public AdministratorDto(Administrator a) {
        this.adminId = a.getId();
        this.username = a.getUsername();
        this.name = a.getName();
        this.status = a.getStatus();
        this.lastLoginAt = a.getLastLoginAt();
        this.createdAt = a.getCreatedAt();
        this.updatedAt = a.getUpdatedAt();
    }
}
