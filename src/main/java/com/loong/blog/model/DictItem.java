package com.loong.blog.model;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loong.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典项目表
 * </p>
 *
 * @author loong
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_dict_item")
public class DictItem extends BaseModel<DictItem> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 字典代码
     */
    private String dicCode;

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 上级代码
     */
    private String pcode;

    @Override
    public QueryWrapper getQueryWrapper() {
        QueryWrapper queryWrapper = super.getQueryWrapper();
        if(null != id) queryWrapper.eq("id", id);
        if(StrUtil.isNotBlank(dicCode)) queryWrapper.eq("dic_code", dicCode);
        if(StrUtil.isNotBlank(code)) queryWrapper.eq("code", code);
        if(StrUtil.isNotBlank(name)) queryWrapper.eq("name", name);
        if(StrUtil.isNotBlank(pcode)) queryWrapper.eq("pcode", pcode);
        return queryWrapper;
    }
}
