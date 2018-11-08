package com.xiaoyong.hrm.customer.service;/**
 * Created by atlantisholic on 2018/8/24.
 */

import com.xiaoyong.hrm.customer.domain.CustomerFollow;
import com.xiaoyong.hrm.support.service.BaseService;

/**
 * @ClassName CustomerFollowService
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/24 9:22
 * @Version 1.0.0
 **/
public interface CustomerFollowService extends BaseService<CustomerFollow, Integer> {

    /**
     * 找到该客户最近的跟进信息
     * @param contactId
     * @return
     */
    public CustomerFollow findLastFollow(int contactId);

}
