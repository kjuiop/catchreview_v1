package io.gig.cathreview.web.controller;

import io.gig.catchreview.core.domain.mark.dto.MarkCreateForm;
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
@RequestMapping("mark")
@RequiredArgsConstructor
public class MarkController {

    @GetMapping("new")
    public String createForm(Model model) {

        model.addAttribute("markCreateForm", new MarkCreateForm());

        return "mark/createForm";
    }
}
