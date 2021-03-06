package com.loong.blog.model;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loong.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author loong
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_article")
public class Article extends BaseModel<Article> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    private String summary;

    private String img;

    private Integer hits;
    private Integer praises;

    /**
     * 内容
     */
    private String content;

    /**
     * 分类
     */
    private String category;

    /**
     * 发布人
     */
    private String author;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 发布状态
     */
    private String publishStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    @Override
    public QueryWrapper getQueryWrapper() {
        QueryWrapper queryWrapper = super.getQueryWrapper();
        if(StrUtil.isNotBlank(this.category)) queryWrapper.eq("category", this.category);
        return queryWrapper;
    }
}
