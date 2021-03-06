package com.loong.blog.web.admin;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.blog.model.Article;
import com.loong.blog.service.IArticleService;
import com.loong.common.pojo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author loong
 * @since 2020-03-23
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    IArticleService articleService;

    @RequestMapping("/list.html")
    public String page() {
        return "admin/article/list";
    }

    @RequestMapping("/form.html")
    public String form(@RequestParam Map<String, Object> param, Model model) {
        Article entity = BeanUtil.mapToBean(param, Article.class, true);
        if(entity.getId() != null) {
            entity = articleService.getById(entity.getId());
        }
        model.addAttribute(entity);
        model.addAllAttributes(param);
        return "admin/article/form";
    }

    @RequestMapping("/crud")
    @ResponseBody
    public Object crud(@RequestHeader String per, @RequestParam Map<String, Object> param) {
        Article entity = BeanUtil.mapToBean(param, Article.class, true);
        switch (per) {
            case "insert":
            case "update":
                Matcher matcher = Pattern.compile("<img.*src\\s*=\\s*(.*?)[^>]*?>").matcher(entity.getContent());
                if(matcher.find()) {
                    Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(matcher.group());
                    if (m.find()) {
                        entity.setImg(m.group(1));
                        entity.setSummary(entity.getSummary());
                    }
                }
                articleService.saveOrUpdate(entity);
                break;
            case "delete":
                articleService.removeById(entity);
                break;
        }
        return ApiResult.createSuccess();
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam Map<String, Object> param) {
        Article entity = BeanUtil.mapToBean(param, Article.class, true);
        Page page = entity.getPage(param);
        Page rsPage = articleService.page(page);
        return ApiResult.createSuccess(rsPage.getTotal(), rsPage.getRecords());
    }
}

