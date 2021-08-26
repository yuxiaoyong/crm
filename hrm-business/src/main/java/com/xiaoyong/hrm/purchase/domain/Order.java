package com.xiaoyong.hrm.purchase.domain;

import com.xiaoyong.hrm.customer.domain.Customer;
import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Order
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/26 22:08
 * @Version V1.0
 **/
@Entity
@Table(name = "crm_order")
public class Order extends BaseEntity {

    /** 客户信息 */
    private Customer customer;
    /** 订单流水号 */
    private String serialNo;
    /** 订购时间 */
    private Date orderTime;
    /** 订单价格 */
    private BigDecimal orderPrice;
    /** 快递价格 */
    private BigDecimal expressPrice;
    /** 快递单号 */
    private String expressNo;
    /** 订单详情 */
    private List<OrderProduct> orderProducts;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "serial_no")
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Column(name = "order_time")
    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Column(name = "order_price")
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Column(name = "express_price")
    public BigDecimal getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(BigDecimal expressPrice) {
        this.expressPrice = expressPrice;
    }

    @Column(name = "express_no")
    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
