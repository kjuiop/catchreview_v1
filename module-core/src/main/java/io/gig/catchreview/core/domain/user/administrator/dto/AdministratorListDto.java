package io.gig.catchreview.core.domain.user.administrator.dto;

import io.gig.catchreview.core.domain.user.administrator.Administrator;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jake
 * @date : 2021-08-26
 */
@Getter
@NoArgsConstructor
public class AdministratorListDto extends AdministratorDto {

    private String createdByUsername;

    public AdministratorListDto(Administrator a) {
        super(a);
        if (a.getCreatedByAdmin() != null) {
            this.createdByUsername = a.getCreatedByAdmin().getUsername();
        }
    }
}
