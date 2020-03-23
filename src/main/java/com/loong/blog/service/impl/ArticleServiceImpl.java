package com.loong.blog.service.impl;

import com.loong.blog.model.Article;
import com.loong.blog.mapper.ArticleMapper;
import com.loong.blog.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author loong
 * @since 2020-03-23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
