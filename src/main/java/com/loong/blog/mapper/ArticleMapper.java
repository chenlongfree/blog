package com.loong.blog.mapper;

import com.loong.blog.model.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author loong
 * @since 2020-03-23
 */
public interface ArticleMapper extends BaseMapper<Article> {

    @Update("update t_article set hits = hits+1 where id = #{id}")
    int updateHits(Article entity);
}
