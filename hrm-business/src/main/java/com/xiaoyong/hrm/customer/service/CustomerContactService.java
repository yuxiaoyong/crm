package com.xiaoyong.hrm.customer.service;/**
 * Created by atlantisholic on 2018/8/19.
 */

import com.xiaoyong.hrm.customer.domain.CustomerContact;
import com.xiaoyong.hrm.customer.model.CustomerContactQuery;
import com.xiaoyong.hrm.support.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @ClassName CustomerContactService
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/19 17:30
 * @Version 1.0.0
 **/
public interface CustomerContactService extends BaseService<CustomerContact, Integer> {

    public Page<CustomerContact> findByQuery(CustomerContactQuery query, Pageable pageable);

}
