package com.xiaoyong.hrm.system.domain;/**
 * Created by atlantisholic on 2018/9/3.
 */

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName SysAttachmentRepository
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/3 22:15
 * @Version 1.0.0
 **/
public interface SysAttachmentRepository extends MongoRepository<SysAttachment, String> {

    SysAttachment findByMd5(String md5);

}
