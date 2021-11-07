package io.gig.cathreview.web.controller;

import io.gig.catchreview.core.domain.sign.dto.SignApplyForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Jake
 * @date : 2021-11-07
 */
@Controller
@RequestMapping("sign")
@RequiredArgsConstructor
public class SignController {

    @GetMapping("apply")
    public String applyForm(Model model) {

        model.addAttribute("signApplyForm", new SignApplyForm());

        return "sign/apply";
    }
}
