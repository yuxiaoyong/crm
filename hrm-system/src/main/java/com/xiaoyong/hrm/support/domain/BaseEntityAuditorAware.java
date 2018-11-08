package com.xiaoyong.hrm.support.domain;/**
 * Created by atlantisholic on 2018/9/5.
 */

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName EntityAuditorAware
 * @Description 实体对象操作用户审计
 * @Author 郁晓勇
 * @Date 2018/9/5 14:03
 * @Version 1.0.0
 **/
@Component
public class BaseEntityAuditorAware implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        return Optional.of(1);
    }

}
