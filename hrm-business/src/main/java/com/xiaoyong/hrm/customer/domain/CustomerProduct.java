package com.xiaoyong.hrm.customer.domain;/**
 * Created by atlantisholic on 2018/8/29.
 */

import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName CustomerProduct
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/29 21:38
 * @Version 1.0.0
 **/
@Entity
@Table(name = "crm_customer_product")
public class CustomerProduct extends BaseEntity {

    //客户信息
    private Integer customerId;
    //产品信息
    private Integer productId;
    //样品数量
    private Integer number;

    @Column(name = "customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
