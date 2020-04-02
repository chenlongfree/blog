package com.loong.blog.service.impl;

import com.loong.blog.model.Album;
import com.loong.blog.mapper.AlbumMapper;
import com.loong.blog.service.IAlbumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 相册 服务实现类
 * </p>
 *
 * @author loong
 * @since 2020-03-25
 */
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements IAlbumService {

    @Override
    public void removeAllPhoto(Album entity) {
        this.baseMapper.deleteAllPhoto(entity);
    }
}
