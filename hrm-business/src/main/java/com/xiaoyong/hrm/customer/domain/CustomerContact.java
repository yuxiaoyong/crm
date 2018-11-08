package com.xiaoyong.hrm.customer.domain;/**
 * Created by atlantisholic on 2018/8/6.
 */

import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName CustomerContact
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/6 12:38
 * @Version 1.0.0
 **/
@Entity
@Table(name = "crm_customer_contact")
public class CustomerContact extends BaseEntity{

    private static final long serialVersionUID = -754722958951216848L;

    public CustomerContact(){}

    public CustomerContact(int contactId){
        setId(contactId);
    }

    //用户姓名
    private String realname;
    //用户性别
    private String gender;
    //手机号码
    private String mobile;
    //座机号码
    private String telephone;
    //传真号码
    private String fax;
    //电子邮箱
    private String email;
    //QQ号码
    private String qqNo;
    //WX号码
    private String wxNo;
    //用户职务
    private String post;
    //所属客户
    private Customer customer;

    @Column(name = "realname", length = 50, nullable = false)
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Column(name = "gender", nullable = false)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "mobile", nullable = true)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "telephone", nullable = true)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(name = "fax", nullable = true)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Column(name = "email", length = 50, nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Column(name = "post", length = 50, nullable = true)
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
