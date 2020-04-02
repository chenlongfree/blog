package com.loong.blog.web.front;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.blog.model.Article;
import com.loong.blog.service.IArticleService;
import com.loong.common.constant.CacheConstant;
import com.loong.common.pojo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author loong
 * @since 2020-03-23
 */
@Controller("FrontArticleController")
@RequestMapping("/front/article")
public class ArticleController {

    @Autowired
    IArticleService articleService;

    @RequestMapping("/page.html")
    public String page(@RequestParam Map<String, Object> param, Model model) {
        Article entity = BeanUtil.mapToBean(param, Article.class, true);
        Page<Article> page = articleService.page(entity.getPage(param), entity.getQueryWrapper());
        for (Article a: page.getRecords()) {
            a.setCategory(CacheConstant.dicMap.get("article_category").get(a.getCategory()));
        }
        model.addAttribute(page);
        return "front/article/page";
    }

    @RequestMapping("/detail.html")
    public String form(@RequestParam Map<String, Object> param, Model model) {
        Article entity = BeanUtil.mapToBean(param, Article.class, true);
        if(entity.getId() != null) {
            entity = articleService.getById(entity.getId());
            entity.setCategory(CacheConstant.dicMap.get("article_category").get(entity.getCategory()));

            articleService.updateHits(entity);
        }
        model.addAttribute(entity);
        model.addAllAttributes(param);
        return "front/article/detail";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam Map<String, Object> param) {
        Article entity = BeanUtil.mapToBean(param, Article.class, true);
        Page page = entity.getPage(param);
        Page rsPage = articleService.page(page, entity.getQueryWrapper());
        return ApiResult.createSuccess(rsPage.getTotal(), rsPage.getRecords());
    }
}

