package io.gig.cathreview.web.controller;

import io.gig.catchreview.core.domain.mark.MarkService;
import io.gig.catchreview.core.domain.mark.dto.MarkCreateForm;
import io.gig.catchreview.core.domain.user.CurrentUser;
import io.gig.catchreview.core.domain.user.LoginUser;
import io.gig.catchreview.core.domain.user.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author : Jake
 * @date : 2021-11-07
 */
@Controller
@RequestMapping("mark")
@RequiredArgsConstructor
public class MarkController {

    private final MarkService markService;

    @GetMapping("new")
    public String createForm(Model model) {

        model.addAttribute("markCreateForm", new MarkCreateForm());

        return "mark/createForm";
    }

    @PostMapping("create")
    public String create(       @Valid MarkCreateForm createForm,
                                @CurrentUser LoginUser loginUser,
                                BindingResult errors,
                                RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "redirect:/mark/new";
        }

        markService.createByMember(createForm, loginUser.getUsername());

        return "redirect:/";
    }

}
