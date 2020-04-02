package com.loong.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.blog.model.AlbumPhoto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.Map;

/**
 * <p>
 * 相册图片关联表 Mapper 接口
 * </p>
 *
 * @author loong
 * @since 2020-03-26
 */
public interface AlbumPhotoMapper extends BaseMapper<AlbumPhoto> {

    @Select("select ap.*,a.name album_name, f.src, f.name photo_name from t_album a, t_album_photo ap, t_attachment f\n" +
            "where a.id=ap.album_id and ap.photo_id=f.id ${ew.customSqlSegment}")
    Page<AlbumPhoto> selectMapsPagePhoto(Page page, @Param(Constants.WRAPPER) QueryWrapper<AlbumPhoto> queryWrapper);
}
