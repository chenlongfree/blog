package com.loong.blog.web.admin;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author loong
 * @since 2020-03-23
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @RequestMapping("/list.html")
    public String page(){
        return "admin/article/list";
    }

    @RequestMapping("/form.html")
    public String form(){
        return "admin/article/form";
    }
}

