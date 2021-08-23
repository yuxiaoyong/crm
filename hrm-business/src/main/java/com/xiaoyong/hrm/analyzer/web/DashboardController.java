package com.xiaoyong.hrm.analyzer.web;

import com.xiaoyong.hrm.analyzer.model.CustomerStatusStatVO;
import com.xiaoyong.hrm.analyzer.service.CustomerAnalyzeService;
import com.xiaoyong.hrm.customer.domain.CustomerContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName DashboardController
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/23 22:14
 * @Version V1.0
 **/
@Controller
@RequestMapping("/api/dashboard")
public class DashboardController{

    @Autowired
    CustomerAnalyzeService customerAnalyzeService;

    @GetMapping(value = "/getCustomerStatusStat")
    public ResponseEntity<CustomerStatusStatVO> getCustomerStatusStat(){
        return ResponseEntity.ok(customerAnalyzeService.analyzeCustomStatus());
    }

}
