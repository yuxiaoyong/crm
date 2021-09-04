package com.xiaoyong.hrm.purchase.domain;/**
 * Created by atlantisholic on 2018/9/15.
 */

import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Purchase
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/15 19:25
 * @Version 1.0.0
 **/
@Entity
@Table(name = "crm_purchase")
public class Purchase extends BaseEntity {

    /** 流水号 */
    private String serialNo;
    //单据编号
    private String serialNum;
    //供货单位
    private Supplier supplier;
    //下单日期
    private Date orderDate;
    //交付日期
    private Date deliveryDate;
    //进货数量
    private Integer quantity;
    //成交金额（元）
    private BigDecimal amount;
    //订单详情
    private List<PurchaseDetail> details;

    @Column(name = "serial_no")
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Column(name = "serial_num", length = 20, nullable = false)
    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Column(name = "order_date", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Column(name = "delivery_date")
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "amount", unique = false)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<PurchaseDetail> getDetails() {
        return details;
    }

    public void setDetails(List<PurchaseDetail> details) {
        this.details = details;
    }
}
