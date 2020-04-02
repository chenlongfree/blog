package com.loong.common.ueditor;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.loong.blog.model.Attachment;
import com.loong.blog.service.IAttachmentService;
import com.loong.common.ueditor.define.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ueditor")
public class UeditorController {

    @Value("${myconfig.file-path}")
    public String filePath;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    IAttachmentService attachmentService;

    @RequestMapping("/config")
    @ResponseBody
    public void config(String action, HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Content-Type" , "text/html");

        try (PrintWriter out = response.getWriter()){
            String contextPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            out.write(new ActionEnter(request, contextPath, filePath).exec());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
