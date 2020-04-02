package com.loong.common.ueditor.upload;

import cn.hutool.core.util.IdUtil;
import com.loong.blog.model.Attachment;
import com.loong.blog.service.IAttachmentService;
import com.loong.blog.service.impl.AttachmentServiceImpl;
import com.loong.common.ueditor.PathFormat;
import com.loong.common.ueditor.define.AppInfo;
import com.loong.common.ueditor.define.BaseState;
import com.loong.common.ueditor.define.FileType;
import com.loong.common.ueditor.define.State;
import com.loong.common.util.SnowflakeUtil;
import com.loong.common.util.SpringUtil;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BinaryUploader {

    public static final State save(HttpServletRequest request,
                                   Map<String, Object> conf) {
        //FileItemStream fileStream = null;
        boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

        ServletFileUpload upload = new ServletFileUpload(
                new DiskFileItemFactory());

        if (isAjaxUpload) {
            upload.setHeaderEncoding("UTF-8");
        }

        try {

            //将request 转化成 MultipartHttpServletRequest
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());
            if (multipartFile == null) {
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }

            String savePath = (String) conf.get("savePath");
            String originFileName = multipartFile.getOriginalFilename();
            String suffix = FileType.getSuffixByFilename(originFileName);

            originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
            savePath = savePath + suffix;

            long maxSize = ((Long) conf.get("maxSize")).longValue();

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            long id = SnowflakeUtil.nextId();
            savePath = PathFormat.parse(savePath, id+"");
            String physicalPath = conf.get("rootPath") + savePath;

            File file = new File(physicalPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(file);

            // 将文件信息保存到数据库
            IAttachmentService attachmentService = SpringUtil.getBean(IAttachmentService.class);
            Attachment entity = new Attachment();
            entity.setId(id);
            entity.setName(multipartFile.getOriginalFilename());
            entity.setType(multipartFile.getContentType());
            entity.setLength(multipartFile.getSize());
            entity.setPath(physicalPath);
            entity.setSrc("/file/get?id=" + entity.getId());
            attachmentService.save(entity);

            State storageState = new BaseState(true);
            storageState.putInfo("size", multipartFile.getSize());
            storageState.putInfo("title", multipartFile.getOriginalFilename());
            storageState.putInfo("url", "/file/get?id=" + id);
            storageState.putInfo("type", suffix);
            storageState.putInfo("original", originFileName + suffix);

            return storageState;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }
}
