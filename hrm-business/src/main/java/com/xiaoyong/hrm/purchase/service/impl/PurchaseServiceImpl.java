package com.xiaoyong.hrm.purchase.service.impl;/**
 * Created by atlantisholic on 2018/9/15.
 */

import com.xiaoyong.hrm.purchase.domain.Purchase;
import com.xiaoyong.hrm.purchase.service.PurchaseService;
import com.xiaoyong.hrm.support.service.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName PurchaseServiceImpl
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/15 21:12
 * @Version 1.0.0
 **/
@Service
public class PurchaseServiceImpl extends BaseServiceImpl<Purchase, Integer> implements PurchaseService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Purchase save(Purchase entity) {
        if(StringUtils.isBlank(entity.getSerialNo())){
            entity.setSerialNo("PID" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        }
        return super.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
