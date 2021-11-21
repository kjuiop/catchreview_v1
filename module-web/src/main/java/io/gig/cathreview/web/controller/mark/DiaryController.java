package io.gig.cathreview.web.controller.mark;

import io.gig.catchreview.core.domain.common.dto.BasePageDto;
import io.gig.catchreview.core.domain.mark.diary.DiaryService;
import io.gig.catchreview.core.domain.mark.diary.dto.DiaryCreateForm;
import io.gig.catchreview.core.domain.mark.diary.dto.DiaryDetailDto;
import io.gig.catchreview.core.domain.mark.diary.dto.DiaryListDto;
import io.gig.catchreview.core.domain.mark.diary.dto.DiarySearchDto;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkCreateForm;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkDetailDto;
import io.gig.catchreview.core.domain.user.CurrentUser;
import io.gig.catchreview.core.domain.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author : Jake
 * @date : 2021-11-14
 */
@Controller
@RequestMapping("mark/{markDetailId}/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping
    public String getDiaryList(@PathVariable(name = "markDetailId") Long markDetailId,
                              DiarySearchDto page,
                              @CurrentUser LoginUser loginUser,
                              Model model) {

        Page<DiaryListDto> diaries = diaryService.getDiaryPageList(markDetailId, page.getPageRequest(), loginUser);

        model.addAttribute("diaries", diaries);

        return "mark/diary/diaryList :: diaries";
    }

    @GetMapping("{diaryId}")
    public String diaryView(@PathVariable(required = true, name = "markDetailId") Long markDetailId,
                            @PathVariable(required = true, name = "diaryId") Long diaryId,
                            Model model) {

        DiaryDetailDto detail = diaryService.getDiaryDetailDto(markDetailId, diaryId);

        model.addAttribute("detail", detail);

        return "mark/diary/view";
    }

    @GetMapping("new")
    public String diaryCreateForm(@PathVariable(name = "markDetailId") Long markDetailId,
                                  Model model) {

        model.addAttribute("diaryCreateForm", new DiaryCreateForm().initCreateForm(markDetailId));

        return "mark/diary/createForm";
    }

    @PostMapping("create")
    public String create(       @PathVariable(name = "markDetailId") Long markDetailId,
                                @Valid DiaryCreateForm createForm,
                                @CurrentUser LoginUser loginUser,
                                BindingResult errors,
                                RedirectAttributes redirectAttributes) {


        if (errors.hasErrors()) {
            return "redirect:/mark/" + markDetailId + "/diary";
        }

        Long diaryId = diaryService.createByMember(createForm, loginUser.getUsername());

        return "redirect:/mark/" + markDetailId + "/diary/" + diaryId;
    }

}
