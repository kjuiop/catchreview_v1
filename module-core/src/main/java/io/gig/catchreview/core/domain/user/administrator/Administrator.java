package io.gig.catchreview.core.domain.user.administrator;

import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.role.Role;
import io.gig.catchreview.core.domain.user.AbstractUser;
import io.gig.catchreview.core.domain.user.UserStatus;
import io.gig.catchreview.core.domain.user.administrator.dto.AdministratorCreateForm;
import io.gig.catchreview.core.domain.user.administrator.dto.AdministratorUpdateForm;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Jake
 * @date : 2021/08/09
 */
@Entity
@Getter @SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Administrator extends AbstractUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_admin_id")
    private Administrator createdByAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_admin_id")
    private Administrator updatedByAdmin;

    @Builder.Default
    @OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<AdministratorRole> administratorRoles = new HashSet<>();

    @Override
    public Set<Role> getRoles() {
        return administratorRoles.stream().map(AdministratorRole::getRole).collect(Collectors.toSet());
    }

    public static Administrator initAdministrator(String username, String password, String name) {
        return Administrator.builder()
                .username(username)
                .password(password)
                .name(name)
                .isEmailValid(YnType.Y)
                .status(UserStatus.NORMAL)
                .build();
    }

    public void addRole(AdministratorRole role) {
        this.administratorRoles.add(role);
    }

    public boolean existCreatedBy(Administrator a) {
        return a.getCreatedByAdmin() != null;
    }

    public boolean existUpdatedBy(Administrator a) {
        return a.getCreatedByAdmin() != null;
    }

    public static Administrator create(AdministratorCreateForm createForm, String encodedPassword) {
        return Administrator.builder()
                .username(createForm.getUsername())
                .name(createForm.getName())
                .password(encodedPassword)
                .passwordFailureCount(0)
                .status(createForm.getStatus())
                .isEmailValid(YnType.Y)
                .build();
    }

    public void update(AdministratorUpdateForm form, String encodedPassword) {
        this.setPassword(encodedPassword);
        this.setStatus(form.getStatus());
    }

    public void createAdministratorRoles(List<Role> roles) {
        roles.stream().map(role -> AdministratorRole.addAdministratorRole(this, role))
                .forEach(administratorRole -> this.getAdministratorRoles().add(administratorRole));
    }

    public void updateAdministratorRoles(List<Role> roles) {
        this.administratorRoles.clear();
        roles.stream().map(role -> AdministratorRole.addAdministratorRole(this, role))
                .forEach(administratorRole -> this.getAdministratorRoles().add(administratorRole));
    }

    public boolean passwordValid(String inputPassword) {
        return this.getPassword().equals(inputPassword);
    }
}
