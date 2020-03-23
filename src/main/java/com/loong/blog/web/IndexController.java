package com.loong.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("index.html")
    public String toIndex(Model model) {
        return "index";
    }

    @RequestMapping("details.html")
    public String toDetails(Model model) {
        return "details";
    }

    @RequestMapping("about.html")
    public String toAbout(Model model) {
        return "about";
    }

    @RequestMapping("album.html")
    public String toAlbum(Model model) {
        return "album";
    }

    @RequestMapping("leacots.html")
    public String toLeacots(Model model) {
        return "leacots";
    }

    @RequestMapping("whisper.html")
    public String toWhisper(Model model) {
        return "whisper";
    }
}
