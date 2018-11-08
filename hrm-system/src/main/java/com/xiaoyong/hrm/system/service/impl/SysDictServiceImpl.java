package com.xiaoyong.hrm.system.service.impl;
/**
 * Created by atlantisholic on 2018/8/6.
 */

import com.xiaoyong.hrm.support.service.BaseServiceImpl;
import com.xiaoyong.hrm.system.domain.SysDict;
import com.xiaoyong.hrm.system.domain.SysDictRepository;
import com.xiaoyong.hrm.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysDictServiceImpl
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/6 21:43
 * @Version 1.0.0
 **/
@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDict, Integer> implements SysDictService {

    @Autowired
    SysDictRepository sysDictRepository;

    @Override
    public SysDict findByTypeAndValue(String type, String value) {
        SysDict dict = new SysDict();
        dict.setDictType(type);
        dict.setDictValue(value);
        List<SysDict> list = sysDictRepository.findAll(Example.of(dict));
        return list.isEmpty()? null: list.get(0);
    }
}
