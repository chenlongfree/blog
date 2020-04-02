package com.loong.blog.web;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loong.blog.model.DictItem;
import com.loong.blog.service.IDictItemService;
import com.loong.common.constant.CacheConstant;
import com.loong.common.pojo.ApiResult;
import com.loong.common.pojo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典项目表 前端控制器
 * </p>
 *
 * @author loong
 * @since 2020-03-24
 */
@Controller
@RequestMapping("/dict/item")
public class DictItemController {

    @Autowired
    IDictItemService dictItemService;



    @RequestMapping("/tree/{dicCode}")
    @ResponseBody
    public Object tree(@PathVariable String dicCode){
        List<Tree> data = dictItemService.treeByDicCode(dicCode);
        return ApiResult.createSuccess(data);
    }

    @RequestMapping("/listAll")
    @ResponseBody
    public Object listAll(@RequestParam Map<String, Object> param){
        DictItem entity = BeanUtil.mapToBean(param, DictItem.class, true);
        List<DictItem> list = dictItemService.list(entity.getQueryWrapper());
        return ApiResult.createSuccess(list.size(), list);
    }

    @RequestMapping("/dicMap")
    @ResponseBody
    public Object dicMap(){
        return ApiResult.createSuccess(CacheConstant.dicMap);
    }
}

