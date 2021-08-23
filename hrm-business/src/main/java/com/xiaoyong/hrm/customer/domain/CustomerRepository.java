package com.xiaoyong.hrm.customer.domain;/**
 * Created by atlantisholic on 2018/8/16.
 */

import com.xiaoyong.hrm.support.domain.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName CustomerRepository
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/16 23:11
 * @Version 1.0.0
 **/
public interface CustomerRepository extends BaseRepository<Customer, Integer> {

    @Query("select status, count(*) from Customer where deleted=false group by status")
    List<Object[]> getCustomerStatusStat();

}
