package com.xiaoyong.hrm.purchase.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoyong.hrm.purchase.common.StockChangeType;
import com.xiaoyong.hrm.purchase.domain.OrderProduct;
import com.xiaoyong.hrm.purchase.domain.StockDetail;
import com.xiaoyong.hrm.purchase.service.StockDetailService;
import com.xiaoyong.hrm.support.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;

/**
 * @ClassName OrderProductListener
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/28 11:54
 * @Version V1.0
 **/
@Configurable
public class OrderProductListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private StockDetailService getStockDetailService(){
        return SpringContextHolder.getBean(StockDetailService.class);
    }

    @PostPersist
    public void postPersist(OrderProduct target){
        StockDetail stock = new StockDetail();
        stock.setChangeQuantity(-target.getQuantity());
        stock.setChangeType(StockChangeType.ORDER.name());
        stock.setChangeRemark(target.getOrderItem().getCustomer().getName());
        stock.setChangeSerialNo(target.getOrderItem().getSerialNo());
        stock.setChangeTime(target.getUpdateTime());
        stock.setProduct(target.getProduct());
        getStockDetailService().save(stock);
    }

    @PostUpdate
    public void postUpdate(OrderProduct target){
        new Thread(() -> {
            StockDetail detail = getStockDetailService().findByChangeSerialNoAndProductId(target.getOrderItem().getSerialNo(), target.getProduct().getId());
            if(detail == null){
                logger.warn("未能找到对应的库存信息, 订单编号：{}, 产品ID: {}, 产品数量：{}", target.getOrderItem().getSerialNo(), target.getProduct().getId(), target.getQuantity());
                return;
            }
            detail.setChangeQuantity(-target.getQuantity());
            detail.setChangeTime(target.getUpdateTime());
            getStockDetailService().save(detail);
        }).start();
    }

    @PostRemove
    public void postRemove(OrderProduct target){
        new Thread(() -> {
            StockDetail detail = getStockDetailService().findByChangeSerialNoAndProductId(target.getOrderItem().getSerialNo(), target.getProduct().getId());
            if(detail == null){
                logger.warn("未能找到对应的库存信息, 订单编号：{}, 产品ID: {}, 产品数量：{}", target.getOrderItem().getSerialNo(), target.getProduct().getId(), target.getQuantity());
                return;
            }
            getStockDetailService().deleteById(detail.getId(), true);
        }).start();
    }

}
