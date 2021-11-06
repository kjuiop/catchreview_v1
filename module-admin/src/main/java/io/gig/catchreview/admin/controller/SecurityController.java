package io.gig.catchreview.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Jake
 * @date : 2021-11-06
 */

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
