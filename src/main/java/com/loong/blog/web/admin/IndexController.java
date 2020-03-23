package com.loong.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController {

    @RequestMapping("index.html")
    public String toIndex(Model model) {
        return "admin/index";
    }
}
