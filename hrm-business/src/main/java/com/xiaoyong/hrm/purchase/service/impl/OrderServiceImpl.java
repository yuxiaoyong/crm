package com.xiaoyong.hrm.purchase.service.impl;

import com.xiaoyong.common.util.StringUtils;
import com.xiaoyong.hrm.purchase.domain.Order;
import com.xiaoyong.hrm.purchase.service.OrderService;
import com.xiaoyong.hrm.support.service.BaseServiceImpl;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/26 22:39
 * @Version V1.0
 **/
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Integer> implements OrderService {

    @Transactional
    @Override
    public Order save(Order entity) {
        if(StringUtils.isEmpty(entity.getSerialNo())){
            entity.setSerialNo("OID" + DateFormatUtils.format(new Date(), "yyyyMMddHHmm"));
        }
        return super.save(entity);
    }
}
