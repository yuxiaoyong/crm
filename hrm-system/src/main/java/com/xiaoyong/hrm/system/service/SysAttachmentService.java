package com.xiaoyong.hrm.system.service;/**
 * Created by atlantisholic on 2018/8/26.
 */

import com.xiaoyong.hrm.system.domain.SysAttachment;

/**
 * @ClassName SysAttachmentService
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/26 13:56
 * @Version 1.0.0
 **/
public interface SysAttachmentService {

    public SysAttachment save(SysAttachment attachment);

    public SysAttachment findByFileId(String fileId);

    public SysAttachment findByMd5(String md5);

}
