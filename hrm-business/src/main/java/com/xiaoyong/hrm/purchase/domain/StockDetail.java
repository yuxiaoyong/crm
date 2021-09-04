package com.xiaoyong.hrm.purchase.domain;

import com.xiaoyong.hrm.product.domain.Product;
import com.xiaoyong.hrm.support.domain.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName StockDetail
 * @Description 库存详情表,包含入库和出库信息
 * @Author yuxiaoyong
 * @Date 2021/8/26 21:48
 * @Version V1.0
 **/
@Entity
@Table(name = "crm_stock_detail")
public class StockDetail extends BaseEntity {

    /** 产品信息 */
    private Product product;
    /** 变更数量 */
    private Integer changeQuantity;
    /** 变更说明 */
    private String changeRemark;
    /** 变更时间 */
    private Date changeTime;
    /** 变更类型 */
    private String changeType;
    /** 变更流水号（订单号或采购编号） */
    private String changeSerialNo;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "change_quantity")
    public Integer getChangeQuantity() {
        return changeQuantity;
    }

    public void setChangeQuantity(Integer changeQuantity) {
        this.changeQuantity = changeQuantity;
    }

    @Column(name = "change_remark")
    public String getChangeRemark() {
        return changeRemark;
    }

    public void setChangeRemark(String changeRemark) {
        this.changeRemark = changeRemark;
    }

    @Column(name = "change_type")
    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    @Column(name = "change_serial_no")
    public String getChangeSerialNo() {
        return changeSerialNo;
    }

    public void setChangeSerialNo(String changeSerialNo) {
        this.changeSerialNo = changeSerialNo;
    }

    @Column(name = "change_time")
    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }
}
