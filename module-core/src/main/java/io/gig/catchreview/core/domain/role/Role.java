package io.gig.catchreview.core.domain.role;

import io.gig.catchreview.core.domain.common.BaseTimeEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author : Jake
 * @date : 2021/08/09
 */
@Entity
@Getter @SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Role extends BaseTimeEntity implements Serializable {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Builder.Default
    private int sortOrder = 0;

    public static Role createRole(String name, String description, int sortOrder) {
        return Role.builder()
                .name(name)
                .description(description)
                .sortOrder(sortOrder)
                .build();
    }

}
