package io.gig.catchreview.core.domain.user.administrator;

import io.gig.catchreview.core.domain.common.BaseTimeEntity;
import io.gig.catchreview.core.domain.role.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Jake
 * @date : 2021/08/10
 */
@Entity
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AdministratorRole extends BaseTimeEntity implements Serializable {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_role_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Administrator administrator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_name")
    private Role role;

    public static AdministratorRole addAdministratorRole(Administrator administrator, Role role) {
        return AdministratorRole.builder()
                .administrator(administrator)
                .role(role)
                .build();
    }

    public void addAdministratorRoles(List<Role> roles) {
        List<AdministratorRole> result = new ArrayList<>();
        for (Role role : roles) {
            AdministratorRole administratorRole = AdministratorRole.builder()
                    .administrator(this.administrator)
                    .role(role)
                    .build();
            result.add(administratorRole);
        }
    }

}
