package com.xiaoyong.hrm.purchase.domain;/**
 * Created by atlantisholic on 2018/9/15.
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.xiaoyong.hrm.product.domain.Product;
import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @ClassName PurchaseDetail
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/15 19:27
 * @Version 1.0.0
 **/
@Entity
@Table(name = "crm_purchase_detail")
public class PurchaseDetail extends BaseEntity{

    //订单信息
    private Purchase purchase;
    //产品信息
    private Product product;
    //产品数量
    private Integer quantity;
    //产品单价
    private BigDecimal unitPrice;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "unit_price")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
