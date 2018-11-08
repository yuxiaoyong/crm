package com.xiaoyong.hrm.customer.domain;/**
 * Created by atlantisholic on 2018/8/6.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Customer
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/6 12:38
 * @Version 1.0.0
 **/
@Entity
@Table(name = "crm_customer")
public class Customer extends BaseEntity{

    private static final long serialVersionUID = 2180157830579378851L;
    //客户名称
    private String name;
    //客户地址
    private String address;
    //客户所在省
    private String province;
    //客户所在市
    private String city;
    //经度
    private Float lon;
    //纬度
    private Float lat;
    //所属行业
    private String trade;
    //客户等级
    private Integer rank;
    //客户简介
    private String introduction;
    //客户来源
    private String source;
    //获得时间
    private Date gainTime;
    //客户状态
    private String status;
    //联系人信息
    private List<CustomerContact> contacts;
    //产品样品信息
    private List<CustomerProduct> products;

    @Column(name = "name", length = 200, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address", length = 500, nullable = true)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "lon", nullable = true)
    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    @Column(name = "lat", nullable = true)
    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    @Column(name = "trade", length = 50, nullable = true)
    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    @Column(name = "rank", nullable = false)
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Lob
    @Column(name = "introduction", nullable = true)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Column(name = "source", length = 50, nullable = true)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Column(name = "gain_time", nullable = false)
    public Date getGainTime() {
        return gainTime;
    }

    public void setGainTime(Date gainTime) {
        this.gainTime = gainTime;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    public List<CustomerContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<CustomerContact> contacts) {
        this.contacts = contacts;
    }

    @JoinColumn(name = "customer_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CustomerProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CustomerProduct> products) {
        this.products = products;
    }
}
