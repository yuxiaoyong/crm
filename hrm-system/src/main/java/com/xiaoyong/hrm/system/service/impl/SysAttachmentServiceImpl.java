package com.xiaoyong.hrm.system.service.impl;/**
 * Created by atlantisholic on 2018/8/26.
 */

import com.xiaoyong.hrm.system.domain.SysAttachment;
import com.xiaoyong.hrm.system.domain.SysAttachmentRepository;
import com.xiaoyong.hrm.system.service.SysAttachmentService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @ClassName SysAttachmentServiceImpl
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/26 13:59
 * @Version 1.0.0
 **/
@Service
public class SysAttachmentServiceImpl implements SysAttachmentService {

    @Autowired
    SysAttachmentRepository sysAttachmentRepository;

    @Override
    public SysAttachment save(SysAttachment attachment) {
        attachment.setCreateTime(new Date());
        attachment.setMd5(DigestUtils.md5Hex(attachment.getContent()));

        SysAttachment persist = findByMd5(attachment.getMd5());
        if(persist != null){
            attachment.setFileId(persist.getFileId());
            return attachment;
        }else{
            return sysAttachmentRepository.save(attachment);
        }
    }

    @Override
    public SysAttachment findByFileId(String fileId) {
        Optional<SysAttachment> optional = sysAttachmentRepository.findById(fileId);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public SysAttachment findByMd5(String md5) {
        return sysAttachmentRepository.findByMd5(md5);
    }
}
