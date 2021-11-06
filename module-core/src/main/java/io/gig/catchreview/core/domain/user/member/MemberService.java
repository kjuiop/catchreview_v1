package io.gig.catchreview.core.domain.user.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.gig.catchreview.core.domain.email.EmailDto;
import io.gig.catchreview.core.domain.email.EmailInfo;
import io.gig.catchreview.core.domain.role.Role;
import io.gig.catchreview.core.domain.role.RoleService;
import io.gig.catchreview.core.domain.user.LoginUser;
import io.gig.catchreview.core.domain.user.UserService;
import io.gig.catchreview.core.domain.user.UserStatus;
import io.gig.catchreview.core.domain.user.member.dto.*;
import io.gig.catchreview.core.exception.NotFoundException;
import io.gig.catchreview.core.exception.PasswordWrongException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author : Jake
 * @date : 2021/08/11
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService implements UserService<Member> {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;
    private final ObjectMapper objectMapper;
    private final RoleService roleService;

    @Override
    public Member getUser(String username) {
        return memberQueryRepository.getMember(username);
    }

    public Member getUserByProviderEmail(String email) {
        return memberQueryRepository.getMemberByProviderEmail(email);
    }


    public Member getUser(LoginUser loginUser) {
        return memberQueryRepository.getMember(loginUser);
    }

    public Member getUser(Long memberId) {
        return memberQueryRepository.getMember(memberId);
    }

    public Member getUser(String username, UserStatus status) {
        return memberQueryRepository.getMember(username, status);
    }

    @Override
    public boolean existsUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

    public Optional<Member> getUserForOauth(String username) {
        return memberRepository.findByUsername(username);
    }

    public boolean existsByProviderEmail(String email) {
        return memberRepository.existsByProviderEmail(email);
    }

    public boolean existsNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }


    @Transactional
    public Member signUp(@NotNull SignUpForm signUpForm) {

        Member newMember = Member.signUp(signUpForm, passwordEncoder.encode(signUpForm.getPassword()));

        Role role = roleService.findByRoleName("ROLE_USER");
        MemberRole memberRole = MemberRole.builder()
                .role(role)
                .member(newMember)
                .build();

        newMember.addMemberRole(memberRole);
        memberRepository.save(newMember);

        return newMember;
    }

    @Transactional
    public void signUpOAuth(SignUpForm signUpForm) {
        Member newMember = Member.signUpOAuth(signUpForm, passwordEncoder.encode(signUpForm.getPassword()));

        Role role = roleService.findByRoleName("ROLE_USER");
        MemberRole memberRole = MemberRole.builder()
                .role(role)
                .member(newMember)
                .build();

        newMember.addMemberRole(memberRole);
        memberRepository.save(newMember);
    }


    @Transactional
    public boolean validateEmail(String code, String username) {

        Member user = getUser(username, UserStatus.PENDING);

        if (user == null) {
            throw new NotFoundException("해당 회원을 찾을 수 없습니다.");
        }

        if (!user.getEmailValidCode().equals(code)) {
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
        }

        user.confirmEmailProcess();

        return true;
    }

    @Transactional
    public void increasePasswordFailureCount(String username) {
        Member user = getUser(username);
        user.increasePasswordFailureCount();
    }

    @Transactional
    public void loginSuccess(String username) {
        try {
            Member user = getUser(username);
            user.loginSuccess();
        } catch (NotFoundException ignore) {

        }
    }

    @Transactional(readOnly = true)
    public Page<MemberListDto> getMemberPageList(MemberSearchDto searchDto) {
        return memberQueryRepository.getMemberPageListBySearch(searchDto);
    }

    @Transactional(readOnly = true)
    public MemberDetailDto getDetail(Long id) {
        Optional<MemberDetailDto> detailDto = memberQueryRepository.getDetailDto(id);
        return detailDto.orElse(MemberDetailDto.emptyDto());
    }

    @Transactional(readOnly = true)
    public MemberDetailDto getDetailByUsername(String username) {
        Optional<MemberDetailDto> detailDto = memberQueryRepository.getDetailDtoByUsername(username);
        return detailDto.orElse(MemberDetailDto.emptyDto());
    }

    @Transactional
    public Long update(MemberUpdateForm updateForm) {
        Member newMember = getUser(updateForm.getUsername());
        newMember.updateByAdministrator(updateForm);
        return memberRepository.save(newMember).getId();
    }

    /**
     * 인코드 할때마다 값이 달라짐
     * 소셜로그인 했을때는 비밀번호 변경 어떻게 ?
     * @param username
     * @param passwordForm
     */
    @Transactional
    public Long modifyPassword(String username, PasswordForm passwordForm) {
        Member foundMember = getUser(username);
        checkEqualPassword(passwordForm.getPassword(), foundMember.getPassword());
        foundMember.updatePassword(passwordEncoder.encode(passwordForm.getNewPassword()));
        return foundMember.getId();
    }

    @Transactional
    public Long memberLeave(String username, LeaveForm leaveForm) {
        Member foundMember = getUser(username);
        checkEqualPassword(leaveForm.getPassword(), foundMember.getPassword());
        foundMember.memberLeave();
        return foundMember.getId();
    }

    private void checkEqualPassword(String inputPw, String originPw) {
        if (!passwordEncoder.matches(inputPw, originPw)) {
            throw new PasswordWrongException("비밀번호가 일치하지 않습니다.");
        }
    }


    @Transactional
    public void forgotPasswordValidCode(String username) {
        Member user = getUser(username);
        user.updateValidCode();

        sendForgotPasswordMail(user);
    }

    private void sendForgotPasswordMail(Member user) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("validCode", user.getEmailValidCode());

        eventPublisher.publishEvent(EmailDto.create(user.getUsername(), EmailInfo.FORGOT_PASSWORD, data));
    }


    public void sendVerifyMail(Member newMember) {

        EmailDto emailDto = EmailDto.create(newMember.getUsername(), EmailInfo.VERIFY, objectMapper.convertValue(newMember, Map.class));

        eventPublisher.publishEvent(emailDto);
    }

    public void confirmPasswordValidCode(String username, String validCode) {

        Member member = getUser(username);

        member.checkValidCode(validCode);

    }

    @Transactional
    public void changePassword(ChangePassword data) {
        Member member = this.getUser(data.getUsername());
        member.updatePassword(passwordEncoder.encode(data.getPassword()));
    }

    public String getUsernameByNickname(String nickname) {

        Optional<Member> findMember = memberRepository.findByNickname(nickname);
        if (findMember.isEmpty()) {
            throw new NotFoundException("not found Member");
        }

        return findMember.get().getUsername();
    }
}
