package io.gig.catchreview.core.domain.user.member;

import io.gig.catchreview.core.domain.common.Utils;
import io.gig.catchreview.core.domain.common.types.YnType;
import io.gig.catchreview.core.domain.role.Role;
import io.gig.catchreview.core.domain.user.AbstractUser;
import io.gig.catchreview.core.domain.user.LoginUser;
import io.gig.catchreview.core.domain.user.UserStatus;
import io.gig.catchreview.core.domain.user.member.dto.MemberUpdateForm;
import io.gig.catchreview.core.exception.UnauthorizedException;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Jake
 * @date : 2021/08/09
 */
@Entity
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Member extends AbstractUser implements Serializable {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String nickname;

    private LocalDateTime joinedAt;

    private LocalDateTime privacyAgreementAt;

    private LocalDateTime marketingAgreementAt;

    // OAuth
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    private String providerId;

    private String providerEmail;

    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<MemberRole> roles = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public static Member signUpOAuth(SignUpForm signUpForm, String encodePassword) {
        LocalDateTime now = LocalDateTime.now();
        return Member.builder()
                .nickname(signUpForm.getNickname())
                .username(signUpForm.getUsername())
                .password(encodePassword)
                .passwordFailureCount(0)
                .status(UserStatus.PENDING)
                .privacyAgreementAt(now)
                .marketingAgreementAt(signUpForm.getMarketingConfirm() == YnType.Y ? now : null)
                .isEmailValid(YnType.Y)
                .joinedAt(now)
                .build();
    }

    @Override
    public Set<Role> getRoles() {
        return roles.stream().map(MemberRole::getRole).collect(Collectors.toSet());
    }


    public static Member signUp(SignUpForm signUpForm, String encodedPassword) {
        LocalDateTime now = LocalDateTime.now();
        return Member.builder()
                .nickname(signUpForm.getNickname())
                .username(signUpForm.getUsername())
                .password(encodedPassword)
                .passwordFailureCount(0)
                .status(UserStatus.PENDING)
                .privacyAgreementAt(signUpForm.getPrivacyConfirm() == YnType.Y ? now : null)
                .marketingAgreementAt(signUpForm.getMarketingConfirm() == YnType.Y ? now : null)
                .isEmailValid(YnType.N)
                .emailValidCode(Utils.randomDigitsGenerator())
                .loginType(LoginType.NORMAL)
                .build();
    }

    public void addMemberRole(MemberRole memberRole) {
        this.roles.add(memberRole);
    }

    public void confirmEmailProcess() {
        LocalDateTime now = LocalDateTime.now();
        this.joinedAt = now;
        this.emailValidationProcess();
    }

    public boolean isEqualUser(LoginUser loginUser) {
        if (loginUser != null) {
            return this.getUsername().equals(loginUser.getUsername());
        }
        return false;
    }

    public void checkEqualsUsername(String loginUsername) {
        if (!this.getUsername().equals(loginUsername)) {
            throw new UnauthorizedException("권한이 없습니다.");
        }
    }

    public void checkEqualPassword(String encodedPassword) {
        if (!this.getPassword().equals(encodedPassword)) {
            throw new InvalidParameterException("기존 비밀번호가 일치하지 않습니다.");
        }
    }

    public void memberLeave() {
        this.setUsername("{WITHDRAW}"+this.getUsername());
        this.setStatus(UserStatus.WITHDRAW);
        this.setWithDrawAt(LocalDateTime.now());
    }

    public void updatePassword(String encodedPassword) {
        this.setPassword(encodedPassword);
    }

    public void updateByAdministrator(MemberUpdateForm form) {
        this.setStatus(form.getStatus());
    }

    public void updateValidCode() {
        this.setEmailValidCode(Utils.randomDigitsGenerator());
    }

    public void checkValidCode(String validCode) {
        if (!this.getEmailValidCode().equals(validCode)) {
            throw new IllegalArgumentException("인증코드가 일치하지 않습니다.");
        }
    }

}
