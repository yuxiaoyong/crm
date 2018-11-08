package com.xiaoyong.hrm.customer.domain;/**
 * Created by atlantisholic on 2018/8/6.
 */

import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CustomerFollow
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/6 12:47
 * @Version 1.0.0
 **/
@Entity
@Table(name = "crm_follow")
public class CustomerFollow extends BaseEntity {

    private static final long serialVersionUID = -9050443237969372299L;
    //跟进时间
    private Date followTime;
    //联系方式
    private String followType;
    //产品名称
    private String productName;
    //产品材料
    private String productMaterial;
    //产品尺寸
    private String productSize;
    //表面处理
    private String surfaceTreat;
    //摩擦系数范围
    private String frictionScope;
    //跟进结果
    private String feedback;
    //联系人信息
    private CustomerContact contact;
    //附件信息
    private List<CustomerFollowAttachment> attachments;

    @Column(name = "follow_time", nullable = false)
    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    @Column(name = "follow_type", nullable = false)
    public String getFollowType() {
        return followType;
    }

    public void setFollowType(String followType) {
        this.followType = followType;
    }

    @Column(name = "product_name", nullable = false)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(name = "product_material", nullable = true)
    public String getProductMaterial() {
        return productMaterial;
    }

    public void setProductMaterial(String productMaterial) {
        this.productMaterial = productMaterial;
    }

    @Column(name = "product_size", nullable = true)
    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    @Column(name = "surface_treat", nullable = true)
    public String getSurfaceTreat() {
        return surfaceTreat;
    }

    public void setSurfaceTreat(String surfaceTreat) {
        this.surfaceTreat = surfaceTreat;
    }

    @Column(name = "friction_scope", nullable = true)
    public String getFrictionScope() {
        return frictionScope;
    }

    public void setFrictionScope(String frictionScope) {
        this.frictionScope = frictionScope;
    }

    @Column(name = "feedback", length = 1000, nullable = false)
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @JoinColumn(name = "contact_id", nullable = false)
    @ManyToOne
    public CustomerContact getContact() {
        return contact;
    }

    public void setContact(CustomerContact contact) {
        this.contact = contact;
    }

    @OneToMany(mappedBy = "customerFollow", orphanRemoval = true, cascade = CascadeType.ALL)
    public List<CustomerFollowAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<CustomerFollowAttachment> attachments) {
        this.attachments = attachments;
    }
}
