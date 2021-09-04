package com.xiaoyong.hrm.purchase.service.impl;

import com.google.common.collect.ImmutableMap;
import com.xiaoyong.hrm.purchase.domain.StockDetail;
import com.xiaoyong.hrm.purchase.service.StockDetailService;
import com.xiaoyong.hrm.support.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName StockDetailServiceImpl
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/26 22:42
 * @Version V1.0
 **/
@Service
public class StockDetailServiceImpl extends BaseServiceImpl<StockDetail, Integer> implements StockDetailService {

    @Transactional
    @Override
    public StockDetail findByChangeSerialNoAndProductId(String changeSerialNo, Integer productId) {
        List<StockDetail> result = findByDslParamMap(ImmutableMap.of("eq(changeSerialNo)", changeSerialNo, "eq(product.id)", productId));
        if(result.isEmpty()){
            return null;
        }
        return result.get(0);
    }
}
