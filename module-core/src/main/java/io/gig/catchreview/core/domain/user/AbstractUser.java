package io.gig.catchreview.core.domain.user;

import io.gig.catchreview.core.domain.common.BaseTimeEntity;
import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author : Jake
 * @date : 2021/08/09
 */
@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractUser extends BaseTimeEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.PENDING;

    private String emailValidCode;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(1) default 'N'")
    private YnType isEmailValid;

    private LocalDateTime lastLoginAt;

    private LocalDateTime withDrawAt;

    @Builder.Default
    @Column(columnDefinition = "integer default 0")
    private Integer passwordFailureCount = 0;

    public abstract Long getId();

    public abstract Set<Role> getRoles();

    public boolean isNormal() {
        return this.status == UserStatus.NORMAL;
    }

    public boolean isValidEmail() {
        return this.isEmailValid == YnType.Y;
    }

    public void emailValidationProcess() {
        this.status = UserStatus.NORMAL;
        this.isEmailValid = YnType.Y;
    }

    public void increasePasswordFailureCount() {
        this.passwordFailureCount += 1;
    }

    public void loginSuccess() {
        this.lastLoginAt = LocalDateTime.now();
        this.passwordFailureCount = 0;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setWithDrawAt(LocalDateTime localDateTime) {
        this.withDrawAt = localDateTime;
    }

    public void setEmailValidCode(String validCode) {
        this.emailValidCode = validCode;
    }
}
