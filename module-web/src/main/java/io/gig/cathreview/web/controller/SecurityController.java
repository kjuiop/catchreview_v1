package io.gig.cathreview.web.controller;

import io.gig.catchreview.core.domain.user.member.Member;
import io.gig.catchreview.core.domain.user.member.MemberService;
import io.gig.catchreview.core.domain.user.member.SignUpForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author : Jake
 * @date : 2021-11-01
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final MemberService memberService;

    private static final String SIGN_UP_COMPLETE_MESSAGE = "회원가입이 완료되었습니다.<br> <strong>이메일 인증 후 로그인해주세요.</strong>";
    private static final String VERIFY_COMPLETE_MESSAGE = "인증에 성공하였습니다. 가입하신 계정으로 로그인해주세요.";
    private static final String VERIFY_FAIL_MESSAGE = "인증에 실패하였습니다.";
    private static final String ADDITIONAL_COMPLETE_MESSAGE = "계정정보가 정상적으로 업데이트되었습니다. 다시 로그인해주세요.";


    @RequestMapping("login")
    public String login(HttpServletRequest request,
                        HttpSession session,
                        Model model) {
        return "security/login";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {

        model.addAttribute("signUpForm", new SignUpForm());

        return "security/sign-up";
    }

    @PostMapping("sign-up")
    public String signUpProcess(@Valid SignUpForm signUpForm,
                                BindingResult errors,
                                RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            return "security/sign-up";
        }

        String nickname = memberService.signUp(signUpForm);

        redirectAttributes.addFlashAttribute("message", SIGN_UP_COMPLETE_MESSAGE);

        return "redirect:/login";
    }
}
