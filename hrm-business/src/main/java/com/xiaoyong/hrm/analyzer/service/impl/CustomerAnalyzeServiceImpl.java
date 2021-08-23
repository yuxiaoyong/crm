package com.xiaoyong.hrm.analyzer.service.impl;

import com.xiaoyong.hrm.analyzer.model.CustomerStatusStatVO;
import com.xiaoyong.hrm.analyzer.service.CustomerAnalyzeService;
import com.xiaoyong.hrm.customer.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CustomerAnalyzeServiceImpl
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/23 22:07
 * @Version V1.0
 **/
@Service
public class CustomerAnalyzeServiceImpl implements CustomerAnalyzeService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerStatusStatVO analyzeCustomStatus() {
        CustomerStatusStatVO vo = new CustomerStatusStatVO();
        customerRepository.getCustomerStatusStat().forEach(objects -> {
            CustomerStatusStatVO.CustomerStatusEntry entry = new CustomerStatusStatVO.CustomerStatusEntry();
            entry.setName((String)objects[0]);
            entry.setValue((long)objects[1]);
            vo.getEntryList().add(entry);
        });
        return vo;
    }

}
