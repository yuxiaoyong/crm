package com.xiaoyong.hrm.purchase.domain;/**
 * Created by atlantisholic on 2018/9/15.
 */

import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName Supplier
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/15 19:37
 * @Version 1.0.0
 **/
@Entity
@Table(name = "crm_supplier")
public class Supplier extends BaseEntity {
    //供应商名称
    private String name;
    //联系人
    private String contact;
    //联系手机
    private String mobile;
    //联系电话
    private String telephone;
    //QQ号码
    private String qqNo;
    //微信号码
    private String wxNo;
    //供应商地址
    private String address;

    @Column(name = "name", length = 200, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "contact", length = 50)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(name = "mobile", length = 50)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "telephone", length = 50)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(name = "qq_no", length = 20)
    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    @Column(name = "wx_no", length = 20)
    public String getWxNo() {
        return wxNo;
    }

    public void setWxNo(String wxNo) {
        this.wxNo = wxNo;
    }

    @Column(name = "address", length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
