package com.loong.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.blog.model.AlbumPhoto;
import com.loong.blog.mapper.AlbumPhotoMapper;
import com.loong.blog.service.IAlbumPhotoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 相册图片关联表 服务实现类
 * </p>
 *
 * @author loong
 * @since 2020-03-26
 */
@Service
public class AlbumPhotoServiceImpl extends ServiceImpl<AlbumPhotoMapper, AlbumPhoto> implements IAlbumPhotoService {

    @Override
    public Page pagePhoto(Page page, QueryWrapper<AlbumPhoto> queryWrapper) {
        return this.baseMapper.selectMapsPagePhoto(page, queryWrapper);
    }
}
