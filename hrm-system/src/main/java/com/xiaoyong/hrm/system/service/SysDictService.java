package com.xiaoyong.hrm.system.service;/**
 * Created by atlantisholic on 2018/8/6.
 */

import com.xiaoyong.hrm.support.service.BaseService;
import com.xiaoyong.hrm.system.domain.SysDict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @ClassName SysDictService
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/6 21:38
 * @Version 1.0.0
 **/
public interface SysDictService extends BaseService<SysDict, Integer> {

    /**
     * 根据条件查询字典信息
     * @param dict
     * @param pageable
     * @return
     */
    Page<SysDict> findByExample(SysDict dict, Pageable pageable);

    /**
     * 根据字典类型和值查询字典信息
     * @param type
     * @param value
     * @return
     */
    SysDict findByTypeAndValue(String type, String value);

}
