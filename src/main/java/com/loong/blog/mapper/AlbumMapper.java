package com.loong.blog.mapper;

import com.loong.blog.model.Album;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 相册 Mapper 接口
 * </p>
 *
 * @author loong
 * @since 2020-03-25
 */
public interface AlbumMapper extends BaseMapper<Album> {

    @Select("delete from t_album_photo where album_id=#{id}")
    void deleteAllPhoto(Album entity);
}
