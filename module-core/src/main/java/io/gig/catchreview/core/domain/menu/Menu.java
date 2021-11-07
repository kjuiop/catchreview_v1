package io.gig.catchreview.core.domain.menu;

import io.gig.catchreview.core.domain.common.BaseTimeEntity;
import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.menu.dto.MenuCreateForm;
import io.gig.catchreview.core.domain.menu.dto.MenuUpdateForm;
import io.gig.catchreview.core.domain.menu.types.AntMatcherType;
import io.gig.catchreview.core.domain.menu.types.MenuType;
import io.gig.catchreview.core.domain.role.Role;
import io.gig.catchreview.core.domain.user.administrator.Administrator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Jake
 * @date : 2021-11-06
 */
@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private String name;

    private String url;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType displayYn = YnType.Y;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType deleteYn = YnType.N;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType activeYn = YnType.Y;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private AntMatcherType antMatcherType = AntMatcherType.Single;

    @Builder.Default
    private int sortOrder = 0;

    private String iconClass;

    @Enumerated(EnumType.STRING)
    private MenuType menuType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PARENT_ID")
    private Menu parent;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("sortOrder ")
    @JoinColumn(name = "PARENT_ID")
    private List<Menu> children = new ArrayList<>();

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "MENU_ROLE",
            joinColumns = @JoinColumn(name = "MENU_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_admin_id")
    private Administrator createdByAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_admin_id")
    private Administrator updatedByAdmin;

    public void addParent(Menu parent) {
        this.parent = parent;
        this.menuType = parent.getMenuType();
        this.antMatcherType = AntMatcherType.All;
        parent.getChildren().add(this);
        parent.antMatcherType = AntMatcherType.Single;
    }

    public static Menu create(MenuCreateForm form) {
        return Menu.builder()
                .name(form.getName())
                .url(form.getUrl())
                .iconClass(form.getIconClass())
                .menuType(form.getMenuType())
                .activeYn(form.getActiveYn())
                .displayYn(form.getDisplayYn())
                .sortOrder(form.getSortOrder())
                .build();
    }

    public void update(MenuUpdateForm form) {
        this.name = form.getName();
        this.url = form.getUrl();
        this.iconClass = form.getIconClass();
        this.menuType = form.getMenuType();
        this.activeYn = form.getActiveYn();
        this.displayYn = form.getDisplayYn();
        this.sortOrder = form.getSortOrder();
    }

    public static Menu initMenu(String name, String url, String iconClass, int sortOrder, Set<Role> roles) {
        return Menu.builder()
                .name(name)
                .url(url)
                .iconClass(iconClass)
                .sortOrder(sortOrder)
                .roles(roles)
                .menuType(MenuType.AdminConsole)
                .build();
    }

    public boolean isUsed() {
        return this.activeYn == YnType.Y &&
                this.deleteYn == YnType.N &&
                this.displayYn == YnType.Y;
    }

    public boolean existParent() {
        return this.parent != null;
    }
}
