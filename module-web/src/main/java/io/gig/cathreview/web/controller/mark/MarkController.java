package io.gig.cathreview.web.controller.mark;

import io.gig.catchreview.core.domain.mark.diary.DiaryService;
import io.gig.catchreview.core.domain.mark.mark.MarkService;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkCreateForm;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkDetailDto;
import io.gig.catchreview.core.domain.user.CurrentUser;
import io.gig.catchreview.core.domain.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    private final DiaryService diaryService;

    @GetMapping("view/diary/{markDetailId}")
    public String diaryView(@PathVariable(required = true, name = "markDetailId") Long markDetailId,
                             Model model) {

        MarkDetailDto detail = markService.getMarkDetailDto(markDetailId);

        model.addAttribute("detail", detail);

        return "mark/diary/diary";
    }

    @GetMapping("view/store/{markDetailId}")
    public String storeView(@PathVariable(required = true, name = "markDetailId") Long markDetailId,
                             Model model) {

        MarkDetailDto detail = markService.getMarkDetailDto(markDetailId);
        model.addAttribute("detail", detail);

        return "mark/store/store";
    }

    @GetMapping("new")
    public String createForm(@RequestParam(required = true, name = "x") String x,
                             @RequestParam(required = true, name = "y") String y,
                             Model model) {

        model.addAttribute("markCreateForm", new MarkCreateForm().initCreateForm(x, y));

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
