package io.gig.cathreview.web.controller.mark;

import io.gig.catchreview.core.domain.mark.diary.dto.DiaryCreateForm;
import io.gig.catchreview.core.domain.mark.diary.dto.DiaryDetailDto;
import io.gig.catchreview.core.domain.mark.diary.dto.DiarySearchDto;
import io.gig.catchreview.core.domain.mark.mark.MarkService;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkDetailDto;
import io.gig.catchreview.core.domain.mark.review.ReviewService;
import io.gig.catchreview.core.domain.mark.review.dto.ReviewCreateForm;
import io.gig.catchreview.core.domain.mark.review.dto.ReviewDetailDto;
import io.gig.catchreview.core.domain.mark.review.dto.ReviewListDto;
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
 * @date : 2021-11-28
 */
@Controller
@RequestMapping("mark/{markDetailId}/store")
@RequiredArgsConstructor
public class StoreController {

    private final MarkService markService;
    private final ReviewService reviewService;

    @GetMapping("intro")
    public String storeIntroView(@PathVariable(required = true, name = "markDetailId") Long markDetailId,
                            Model model) {

        MarkDetailDto detail = markService.getMarkDetailDto(markDetailId);
        model.addAttribute("detail", detail);

        return "mark/store/intro :: store-intro";
    }

    @GetMapping("review")
    public String getReviewList(@PathVariable(name = "markDetailId") Long markDetailId,
                               DiarySearchDto page,
                               @CurrentUser LoginUser loginUser,
                               Model model) {

        Page<ReviewListDto> reviewList = reviewService.getReviewPageList(markDetailId, page.getPageRequest(), loginUser);

        model.addAttribute("reviewList", reviewList);
        model.addAttribute("markDetailId", markDetailId);

        return "mark/store/reviewList :: review";
    }

    @GetMapping("review/new")
    public String reviewCreateForm(@PathVariable(name = "markDetailId") Long markDetailId,
                                  Model model) {

        model.addAttribute("reviewCreateForm", new ReviewCreateForm().initCreateForm(markDetailId));

        return "mark/store/reviewCreateForm";
    }

    @PostMapping("review/create")
    public String create(       @PathVariable(name = "markDetailId") Long markDetailId,
                                @Valid ReviewCreateForm createForm,
                                @CurrentUser LoginUser loginUser,
                                BindingResult errors,
                                RedirectAttributes redirectAttributes) {


        if (errors.hasErrors()) {
            return "redirect:/mark/view/store/" + markDetailId;
        }

        Long reviewId = reviewService.createByMember(createForm, loginUser.getUsername());

        return "redirect:/mark/" + markDetailId + "/store/review/" + reviewId;
    }

    @GetMapping("review/{reviewId}")
    public String reviewView(@PathVariable(required = true, name = "markDetailId") Long markDetailId,
                            @PathVariable(required = true, name = "reviewId") Long reviewId,
                            Model model) {

        ReviewDetailDto detail = reviewService.getReviewDetailDto(markDetailId, reviewId);

        model.addAttribute("detail", detail);

        return "mark/store/view";
    }
}
