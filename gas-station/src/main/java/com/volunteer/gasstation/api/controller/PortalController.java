package com.volunteer.gasstation.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author by huoyao on 2021/8/12.
 */
@Controller
@RequestMapping("/api/portal")
public class PortalController {

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("anquan", "123456");
        model.addAttribute("qiye", "123456");
        return "index";
    }
}
