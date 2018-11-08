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

    public T save(T entity);

    public T create(T entity);

    public T update(T entity);

    public void deleteById(ID id);

    public void deleteByIds(ID[] ids);

    public T findById(ID id);

    public List<T> findByExample(T entity);

    public List<T> findByExample(T entity, Sort sort);

    public Page<T> findByExample(T entity, Pageable pageable);

    public Page<T> findByExample(Example<T> example, Pageable pageable);

    public List<T> findByDslParamMap(Map<String, Object> params);

    public List<T> findByDslParamMap(Map<String, Object> params, Sort sort);

    public Page<T> findByDslParamMap(Map<String, Object> params, Pageable pageable);

}
