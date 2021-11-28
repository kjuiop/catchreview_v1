package io.gig.cathreview.web.controller.mark;

import io.gig.catchreview.core.domain.mark.diary.dto.DiarySearchDto;
import io.gig.catchreview.core.domain.mark.mark.MarkService;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkDetailDto;
import io.gig.catchreview.core.domain.mark.review.ReviewService;
import io.gig.catchreview.core.domain.mark.review.dto.ReviewListDto;
import io.gig.catchreview.core.domain.user.CurrentUser;
import io.gig.catchreview.core.domain.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

        return "mark/store/reviewList :: review";
    }
}
