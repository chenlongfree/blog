package com.loong.blog.service.impl;

import com.loong.blog.model.Attachment;
import com.loong.blog.mapper.AttachmentMapper;
import com.loong.blog.service.IAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件列表 服务实现类
 * </p>
 *
 * @author loong
 * @since 2020-03-24
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {

}
