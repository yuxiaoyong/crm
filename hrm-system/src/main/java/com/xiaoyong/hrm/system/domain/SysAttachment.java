package com.xiaoyong.hrm.system.domain;/**
 * Created by atlantisholic on 2018/8/26.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @ClassName SysAttachment
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/26 13:50
 * @Version 1.0.0
 **/
@Document(collection = "attachment")
public class SysAttachment {
    //文件ID
    @Id
    private String fileId;
    //文件名称
    private String fileName;
    //文件类型
    private String fileType;
    //文件大小
    private long fileSize;
    //文件内容
    private byte[] content;
    //文件MD5校验码
    private String md5;
    //文件创建时间
    private Date createTime;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @JsonIgnore
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
