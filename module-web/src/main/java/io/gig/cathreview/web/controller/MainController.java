package io.gig.cathreview.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Jake
 * @date : 2021-10-30
 */
@Slf4j
@Controller
public class MainController {

    @GetMapping("/")
    public String index(@RequestParam(value = "address", required = false) String address,
                        Model model) {

        if (StringUtils.hasText(address)) {
            model.addAttribute("address", address);
        }

        return "index";
    }

}
