package com.loong.blog.web;


import cn.hutool.core.bean.BeanUtil;
import com.loong.blog.model.DictName;
import com.loong.blog.service.IDictNameService;
import com.loong.common.pojo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author loong
 * @since 2020-03-25
 */
@Controller
@RequestMapping("/dict/name")
public class DictNameController {

    @Autowired
    IDictNameService dictNameService;

    @RequestMapping("/listAll")
    @ResponseBody
    public Object listAll(@RequestParam Map<String, Object> param){
        DictName entity = BeanUtil.mapToBean(param, DictName.class, true);
        List<DictName> list = dictNameService.list(entity.getQueryWrapper());
        return ApiResult.createSuccess(list.size(), list);
    }
}

