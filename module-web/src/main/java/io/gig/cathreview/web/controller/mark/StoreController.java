package io.gig.cathreview.web.controller.mark;

import io.gig.catchreview.core.domain.mark.mark.MarkService;
import io.gig.catchreview.core.domain.mark.mark.dto.MarkDetailDto;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("intro")
    public String storeIntroView(@PathVariable(required = true, name = "markDetailId") Long markDetailId,
                            Model model) {

        MarkDetailDto detail = markService.getMarkDetailDto(markDetailId);
        model.addAttribute("detail", detail);

        return "mark/store/intro :: store-intro";
    }
}
