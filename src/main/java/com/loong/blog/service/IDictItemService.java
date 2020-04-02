package com.loong.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loong.blog.model.DictItem;
import com.loong.common.pojo.Tree;

import java.util.List;

/**
 * <p>
 * 字典项目表 服务类
 * </p>
 *
 * @author loong
 * @since 2020-03-24
 */
public interface IDictItemService extends IService<DictItem> {

    /**
     * 根据字典编码获取字典树
     * @param dicCode
     * @return
     */
    List<Tree> treeByDicCode(String dicCode);
}
