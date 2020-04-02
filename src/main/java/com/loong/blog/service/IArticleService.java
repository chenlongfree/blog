package com.loong.blog.service;

import com.loong.blog.model.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author loong
 * @since 2020-03-23
 */
public interface IArticleService extends IService<Article> {

    @Transactional
    void updateHits(Article entity);
}
