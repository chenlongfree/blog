package com.loong.common.model;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.loong.blog.model.DictItem;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseModel<T> implements Serializable {

    @JsonIgnore
    public Page getPage(Map<String, Object> param){
        int current = Convert.toInt(param.get("page"), -1);
        int size = Convert.toInt(param.get("limit"), 10);
        Page<T> page = new Page<>(current, size);
        return page;
    }

    @JsonIgnore
    public QueryWrapper getQueryWrapper(){
        QueryWrapper<T> wrapper = new QueryWrapper();
        return wrapper;
    }
}
