package com.loong.blog.model;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loong.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 相册图片关联表
 * </p>
 *
 * @author loong
 * @since 2020-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_album_photo")
@NoArgsConstructor
@AllArgsConstructor
public class AlbumPhoto extends BaseModel<AlbumPhoto> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 相册主键
     */
    private Integer albumId;

    /**
     * （图片）文件主键（t_attachment表主键）
     */
    private Long photoId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @Override
    public QueryWrapper<AlbumPhoto> getQueryWrapper() {
        QueryWrapper<AlbumPhoto> wrapper = super.getQueryWrapper();
        if (null != id) wrapper.eq("id", this.id);
        if (null != albumId) wrapper.eq("album_id", this.albumId);
        if (null != photoId) wrapper.eq("photo_id", this.photoId);
        if (StrUtil.isNotBlank(ids)) {
            wrapper.in("id", ids.split(","));
        }
        return wrapper;
    }

    @TableField(exist = false)
    private String photoIdsStr;

    @TableField(exist = false)
    private List<Long> photoIds;

    @TableField(exist = false)
    private String ids;
    @TableField(exist = false)
    private String src;
    @TableField(exist = false)
    private String albumName;
    @TableField(exist = false)
    private String PhotoName;

    public void setPhotoIdsStr (String photoIdsStr) {
        this.photoIdsStr = photoIdsStr;
        photoIds = new ArrayList<>();
        if(StrUtil.isNotBlank(photoIdsStr)) {
            String[] split = photoIdsStr.split(",");
            for (int i = 0; i < split.length; i++) {
                photoIds.add(Convert.toLong(split[i], null));
            }
        }
    }
}
