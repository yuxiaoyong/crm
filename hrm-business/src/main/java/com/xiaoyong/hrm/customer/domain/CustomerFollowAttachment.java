package com.xiaoyong.hrm.customer.domain;/**
 * Created by atlantisholic on 2018/8/6.
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName CustomerFollowAttachment
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/6 20:18
 * @Version 1.0.0
 **/
@Entity
@Table(name = "crm_follow_attachment")
public class CustomerFollowAttachment extends BaseEntity{

    //附件ID
    private String fileId;
    //附件名称
    private String fileName;
    //附件大小
    private long fileSize;
    //文件类型
    private String fileType;
    //所属跟进
    private CustomerFollow customerFollow;

    @Column(name = "file_id", length = 50, nullable = false)
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Column(name = "file_name", length = 200, nullable = false)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "file_size", nullable = false)
    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Column(name = "file_type", length = 50, nullable = false)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "follow_id", nullable = false)
    public CustomerFollow getCustomerFollow() {
        return customerFollow;
    }

    public void setCustomerFollow(CustomerFollow customerFollow) {
        this.customerFollow = customerFollow;
    }
}
