package com.loong.blog.web.admin;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.blog.model.Album;
import com.loong.blog.model.AlbumPhoto;
import com.loong.blog.service.IAlbumPhotoService;
import com.loong.common.pojo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 相册图片关联表 前端控制器
 * </p>
 *
 * @author loong
 * @since 2020-03-26
 */
@Controller
@RequestMapping("/admin/album/photo")
public class AlbumPhotoController {

    @Autowired
    IAlbumPhotoService albumPhotoService;

    @RequestMapping("/list.html")
    public String page() {
        return "admin/album/photo/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam Map<String, Object> param) {
        AlbumPhoto entity = BeanUtil.mapToBean(param, AlbumPhoto.class, true);
        Page rsPage = albumPhotoService.pagePhoto(entity.getPage(param), entity.getQueryWrapper());
        return ApiResult.createSuccess(rsPage.getTotal(), rsPage.getRecords());
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Object batchDelete(@RequestParam Map<String, Object> param) {
        AlbumPhoto entity = BeanUtil.mapToBean(param, AlbumPhoto.class, true);
        QueryWrapper<AlbumPhoto> queryWrapper = entity.getQueryWrapper();
        albumPhotoService.remove(queryWrapper);
        return ApiResult.createSuccess();
    }

    @RequestMapping("/batchSave")
    @ResponseBody
    public Object batchSave(@RequestParam Map<String, Object> param) {
        AlbumPhoto entity = BeanUtil.mapToBean(param, AlbumPhoto.class, true);

        List<AlbumPhoto> list = new ArrayList<>();
        if(null != entity.getPhotoIds()) {
            for (Long photoId : entity.getPhotoIds()) {
                if(null != photoId) {
                    AlbumPhoto e = new AlbumPhoto();
                    e.setAlbumId(entity.getAlbumId());
                    e.setPhotoId(photoId);
                    list.add(e);
                }
            }
        }
        albumPhotoService.saveBatch(list);
        return ApiResult.createSuccess();
    }
}

