package com.xiaoyong.hrm.purchase.listener;

import com.xiaoyong.hrm.purchase.common.StockChangeType;
import com.xiaoyong.hrm.purchase.domain.OrderProduct;
import com.xiaoyong.hrm.purchase.domain.PurchaseDetail;
import com.xiaoyong.hrm.purchase.domain.StockDetail;
import com.xiaoyong.hrm.purchase.domain.Supplier;
import com.xiaoyong.hrm.purchase.service.PurchaseDetailService;
import com.xiaoyong.hrm.purchase.service.StockDetailService;
import com.xiaoyong.hrm.purchase.service.SupplierService;
import com.xiaoyong.hrm.support.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

/**
 * @ClassName OrderProductListener
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/28 11:54
 * @Version V1.0
 **/
@Configurable
public class PurchaseDetailListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private StockDetailService getStockDetailService(){
        return SpringContextHolder.getBean(StockDetailService.class);
    }

    private SupplierService getSupplierService(){
        return SpringContextHolder.getBean(SupplierService.class);
    }

    @PostPersist
    public void postPersist(PurchaseDetail target){
        StockDetail stock = new StockDetail();
        stock.setChangeQuantity(target.getQuantity());
        stock.setChangeType(StockChangeType.PURCHASE.name());
        Integer supplierId = target.getPurchase().getSupplier().getId();
        Supplier supplier = getSupplierService().findById(supplierId);
        stock.setChangeRemark(supplier.getName());
        stock.setChangeSerialNo(target.getPurchase().getSerialNo());
        stock.setChangeTime(target.getUpdateTime());
        stock.setProduct(target.getProduct());
        getStockDetailService().save(stock);
    }

    @PostUpdate
    public void postUpdate(PurchaseDetail target){
        new Thread(() -> {
            StockDetail detail = getStockDetailService().findByChangeSerialNoAndProductId(target.getPurchase().getSerialNo(), target.getProduct().getId());
            if(detail == null){
                logger.warn("未能找到对应的库存信息, 进货编号：{}, 产品ID: {}, 产品数量：{}", target.getPurchase().getSerialNo(), target.getProduct().getId(), target.getQuantity());
                return;
            }
            detail.setChangeQuantity(target.getQuantity());
            detail.setChangeTime(target.getUpdateTime());
            getStockDetailService().save(detail);
        }).start();
    }

    @PostRemove
    public void postRemove(PurchaseDetail target){
        new Thread(() -> {
            StockDetail detail = getStockDetailService().findByChangeSerialNoAndProductId(target.getPurchase().getSerialNo(), target.getProduct().getId());
            if(detail == null){
                logger.warn("未能找到对应的库存信息, 进货编号：{}, 产品ID: {}, 产品数量：{}", target.getPurchase().getSerialNo(), target.getProduct().getId(), target.getQuantity());
                return;
            }
            getStockDetailService().deleteById(detail.getId(), true);
        }).start();
    }

}
