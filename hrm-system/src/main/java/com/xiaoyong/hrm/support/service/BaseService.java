package com.xiaoyong.hrm.support.service;/**
 * Created by atlantisholic on 2018/6/7.
 */


import com.xiaoyong.hrm.support.domain.BaseEntity;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseService
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/6/7 16:04
 * @Version 1.0.0
 **/
public interface BaseService<T extends BaseEntity, ID> {

    T save(T entity);

    T create(T entity);

    T update(T entity);

    void deleteById(ID id);

    void deleteById(ID id, boolean physical);

    void deleteByIds(ID[] ids);

    T findById(ID id);

    List<T> findByExample(T entity);

    List<T> findByExample(T entity, Sort sort);

    Page<T> findByExample(T entity, Pageable pageable);

    Page<T> findByExample(Example<T> example, Pageable pageable);

    List<T> findByDslParamMap(Map<String, Object> params);

    List<T> findByDslParamMap(Map<String, Object> params, Sort sort);

    Page<T> findByDslParamMap(Map<String, Object> params, Pageable pageable);

}
