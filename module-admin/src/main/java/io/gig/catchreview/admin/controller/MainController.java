package io.gig.catchreview.admin.controller;

import io.gig.catchreview.core.utils.InitUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Jake
 * @date : 2021-08-15
 */
@Controller
@RequiredArgsConstructor
public class MainController {

    private final InitUtils initUtils;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("init-data")
    public String initData() {
        initUtils.initData();
        return "redirect:/login";
    }

}
