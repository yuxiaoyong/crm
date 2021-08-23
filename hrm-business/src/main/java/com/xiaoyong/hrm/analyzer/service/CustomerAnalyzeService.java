package com.xiaoyong.hrm.analyzer.service;

import com.xiaoyong.hrm.analyzer.model.CustomerStatusStatVO;

/**
 * @ClassName CustomerAnalyzeService
 * @Description TODO
 * @Author yuxiaoyong
 * @Date 2021/8/23 22:04
 * @Version V1.0
 **/
public interface CustomerAnalyzeService {

    /**
     * 统计客户状态
     * @return
     */
    CustomerStatusStatVO analyzeCustomStatus();

}
