package io.gig.cathreview.web.controller;

import io.gig.catchreview.core.domain.member.SignUpForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

/**
 * @author : Jake
 * @date : 2021-11-01
 */
@Slf4j
@Controller
public class SecurityController {

    @GetMapping("/login")
    public String login(Model model) {
        return "security/login";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {

        model.addAttribute("signUpForm", new SignUpForm());

        return "security/sign-up";
    }
}
