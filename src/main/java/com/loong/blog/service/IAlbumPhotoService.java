package com.loong.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.blog.model.AlbumPhoto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 相册图片关联表 服务类
 * </p>
 *
 * @author loong
 * @since 2020-03-26
 */
public interface IAlbumPhotoService extends IService<AlbumPhoto> {

    Page pagePhoto(Page page, QueryWrapper<AlbumPhoto> queryWrapper);
}
