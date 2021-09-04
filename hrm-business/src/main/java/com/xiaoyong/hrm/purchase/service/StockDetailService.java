package com.xiaoyong.hrm.purchase.service;

import com.xiaoyong.hrm.purchase.domain.StockDetail;
import com.xiaoyong.hrm.support.service.BaseService;

/**
 * @ClassName StockDetailService
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/26 22:42
 * @Version V1.0
 **/
public interface StockDetailService extends BaseService<StockDetail, Integer> {

    /**
     * 根据订单号查询库存信息
     * @param changeSerialNo 流水号
     * @param productId 产品ID
     * @return
     */
    StockDetail findByChangeSerialNoAndProductId(String changeSerialNo, Integer productId);

}
