package com.xiaoyong.hrm.customer.service.impl;/**
 * Created by atlantisholic on 2018/8/24.
 */

import com.xiaoyong.hrm.customer.domain.CustomerContact;
import com.xiaoyong.hrm.customer.domain.CustomerFollow;
import com.xiaoyong.hrm.customer.service.CustomerFollowService;
import com.xiaoyong.hrm.support.service.BaseServiceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CustomerFollowServiceImpl
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/24 9:23
 * @Version 1.0.0
 **/
@Service
public class CustomerFollowServiceImpl extends BaseServiceImpl<CustomerFollow, Integer> implements CustomerFollowService {

    @Override
    public CustomerFollow findLastFollow(int contactId) {

        CustomerFollow follow = new CustomerFollow();
        follow.setContact(new CustomerContact(contactId));

        List<CustomerFollow> follows = findByExample(follow, new Sort(Sort.Direction.DESC, "createTime"));
        if(!follows.isEmpty()){
            return follows.get(0);
        }
        return null;
    }

}
