package com.loong.blog.web.admin;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loong.blog.model.Album;
import com.loong.blog.model.AlbumPhoto;
import com.loong.blog.service.IAlbumPhotoService;
import com.loong.blog.service.IAlbumService;
import com.loong.common.pojo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>
 * 相册 前端控制器
 * </p>
 *
 * @author loong
 * @since 2020-03-25
 */
@Controller
@RequestMapping("/admin/album")
public class AlbumController {

    @Autowired
    IAlbumService albumService;

    @Autowired
    IAlbumPhotoService albumPhotoService;

    @RequestMapping("/list.html")
    public String page() {
        return "admin/album/list";
    }

    @RequestMapping("/form.html")
    public String form(@RequestParam Map<String, Object> param, Model model) {
        Album entity = BeanUtil.mapToBean(param, Album.class, true);
        if(entity.getId() != null) {
            entity = albumService.getById(entity.getId());
        }
        model.addAttribute(entity);
        model.addAllAttributes(param);
        return "admin/album/form";
    }

    @RequestMapping("/crud")
    @ResponseBody
    public Object crud(@RequestHeader String per, @RequestParam Map<String, Object> param) {
        Album entity = BeanUtil.mapToBean(param, Album.class, true);
        switch (per) {
            case "insert":
            case "update":
                albumService.saveOrUpdate(entity);
                break;
            case "delete":
                AlbumPhoto photo = new AlbumPhoto();
                photo.setAlbumId(entity.getId());
                albumPhotoService.remove(photo.getQueryWrapper());
                albumService.removeById(entity);
                break;
        }
        return ApiResult.createSuccess();
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam Map<String, Object> param) {
        Album entity = BeanUtil.mapToBean(param, Album.class, true);
        Page page = entity.getPage(param);
        Page rsPage = albumService.page(page);
        return ApiResult.createSuccess(rsPage.getTotal(), rsPage.getRecords());
    }
}

