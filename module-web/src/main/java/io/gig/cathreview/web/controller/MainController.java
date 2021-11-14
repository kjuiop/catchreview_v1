package io.gig.cathreview.web.controller;

import io.gig.catchreview.core.domain.mark.mark.MarkQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : Jake
 * @date : 2021-10-30
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final MarkQueryRepository markQueryRepository;

    @GetMapping("/")
    public String index(@RequestParam(value = "keyword", required = false) String address,
                        Model model) {

        if (StringUtils.hasText(address)) {
            model.addAttribute("keyword", address);
        }

        model.addAttribute("marks", markQueryRepository.getMarkListForMap());

        return "index";
    }

}
