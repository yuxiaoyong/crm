package com.xiaoyong.hrm.purchase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xiaoyong.hrm.product.domain.Product;
import com.xiaoyong.hrm.purchase.listener.OrderProductListener;
import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName OrderProduct
 * @Description 订单详情
 * @Author yuxiaoyong
 * @Date 2021/8/26 22:11
 * @Version V1.0
 **/
@EntityListeners({OrderProductListener.class})
@Entity
@Table(name = "crm_order_product")
public class OrderProduct extends BaseEntity {

    /** 订单信息 */
    private Order orderItem;
    /** 产品信息 */
    private Product product;
    /** 订购数量 */
    private Integer quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    public Order getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Order orderItem) {
        this.orderItem = orderItem;
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
}
