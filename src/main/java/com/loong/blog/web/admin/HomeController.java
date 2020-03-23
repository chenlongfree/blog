package com.loong.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/admin/home")
public class HomeController {

    @RequestMapping("console.html")
    public String toConsole(Model model) {
        return "admin/home/console";
    }

    @RequestMapping("homepage1.html")
    public String toHomePage1(Model model) {
        return "admin/home/homepage1";
    }

    @RequestMapping("homepage2.html")
    public String toHomePage2(Model model) {
        return "admin/home/homepage2";
    }
}
