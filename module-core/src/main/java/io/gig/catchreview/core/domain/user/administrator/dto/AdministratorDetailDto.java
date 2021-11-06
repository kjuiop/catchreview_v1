package io.gig.catchreview.core.domain.user.administrator.dto;

import io.gig.catchreview.core.domain.user.UserStatus;
import io.gig.catchreview.core.domain.user.administrator.Administrator;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Jake
 * @date : 2021-08-26
 */
@SuperBuilder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorDetailDto extends AdministratorDto {

    private static final AdministratorDetailDto EMPTY;

    static {
        EMPTY = AdministratorDetailDto.builder()
                .empty(true)
                .status(UserStatus.PENDING)
                .build();
    }

    @Builder.Default
    private boolean empty = false;

    @Builder.Default
    List<String> roles = new ArrayList<>();

    public static AdministratorDetailDto emptyDto() {
        return EMPTY;
    }

    public AdministratorDetailDto(Administrator a) {
        super(a);
        this.roles = a.getAdministratorRoles().stream().map(role -> role.getRole().getName()).collect(Collectors.toList());
    }

}
