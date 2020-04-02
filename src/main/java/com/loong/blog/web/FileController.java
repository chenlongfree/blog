package com.loong.blog.web;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.loong.blog.model.Attachment;
import com.loong.blog.service.IAttachmentService;
import com.loong.common.pojo.ApiResult;
import com.loong.common.util.SnowflakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * <p>
 * 文件列表 前端控制器
 * </p>
 *
 * @author loong
 * @since 2020-03-24
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Value("${myconfig.file-path}")
    public String filePath;

    @Autowired
    IAttachmentService attachmentService;

    @RequestMapping("/upload_file.html")
    public String page() {
        return "upload_file";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {

        String path = filePath + File.separator + file.getOriginalFilename();
        File dest = new File(path);
        if(!dest.getParentFile().exists()) dest.getParentFile().mkdirs();
        if(!dest.exists()) dest.createNewFile();
        file.transferTo(dest);

        Attachment entity = new Attachment();
        entity.setId(SnowflakeUtil.nextId());
        entity.setName(file.getOriginalFilename());
        entity.setType(file.getContentType());
        entity.setLength(file.getSize());
        entity.setPath(path);
        entity.setSrc("/file/get?id=" + entity.getId());
        attachmentService.save(entity);
        return ApiResult.createSuccess(1, entity);
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void search(Long id, HttpServletResponse response) throws Exception {

        Attachment byId = attachmentService.getById(id);
        if(byId != null) {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType(byId.getType());

            try(InputStream is=new FileInputStream(byId.getPath())){
                BufferedImage bi= ImageIO.read(is);
                ImageIO.write(bi, "JPEG", response.getOutputStream());
            }
        }
    }
}

