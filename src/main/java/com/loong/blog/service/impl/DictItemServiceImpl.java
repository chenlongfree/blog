package com.loong.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loong.blog.model.DictItem;
import com.loong.blog.mapper.DictItemMapper;
import com.loong.blog.service.IDictItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.common.pojo.Tree;
import com.loong.common.util.TreeUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 字典项目表 服务实现类
 * </p>
 *
 * @author loong
 * @since 2020-03-24
 */
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements IDictItemService {

    @Override
    public List<Tree> treeByDicCode(String dicCode) {
        QueryWrapper<DictItem> wrapper = new QueryWrapper<>();
        wrapper.eq("dic_code", dicCode);
        List<DictItem> dictItems = this.baseMapper.selectList(wrapper);
        List<Tree> treeList = new ArrayList<>();
        for (DictItem item : dictItems) {
            treeList.add(new Tree(item.getName(), item.getCode(), item.getPcode()));
        }
        return TreeUtil.getTreeData(treeList);
    }
}
