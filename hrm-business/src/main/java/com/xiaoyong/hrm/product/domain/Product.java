package com.xiaoyong.hrm.product.domain;/**
 * Created by atlantisholic on 2018/8/6.
 */

import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName Product
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/6 21:20
 * @Version 1.0.0
 **/
@Entity
@Table(name = "crm_product")
public class Product extends BaseEntity {

    //产品名称
    private String name;
    //产品型号
    private String type;
    //产品规格
    private String packing;
    //产品简介
    private String introduction;
    //进货单价（元）
    private Float purchasePrice;
    //销售单价（元）
    private Float sellingPrice;

    @Column(name = "name", length = 200, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type", length = 50, nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "introduction", length = 4000)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Column(name = "purchase_price", nullable = false)
    public Float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Column(name = "selling_price", nullable = false)
    public Float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Column(name = "packing")
    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }
}
