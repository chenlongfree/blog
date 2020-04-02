package com.loong.blog.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.loong.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件列表
 * </p>
 *
 * @author loong
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_attachment")
public class Attachment extends BaseModel {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件长度
     */
    private Long length;

    /**
     * 文件路径
     */
    private String path;

    private String src;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
