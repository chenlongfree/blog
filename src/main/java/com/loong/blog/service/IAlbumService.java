package com.loong.blog.service;

import com.loong.blog.model.Album;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 相册 服务类
 * </p>
 *
 * @author loong
 * @since 2020-03-25
 */
public interface IAlbumService extends IService<Album> {

    /**
     * 删除相册想所有图片
     * @param entity
     */
    void removeAllPhoto(Album entity);
}
