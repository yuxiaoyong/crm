package com.xiaoyong.hrm.support.domain;/**
 * Created by atlantisholic on 2018/9/22.
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseRepository
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/22 21:37
 * @Version 1.0.0
 **/
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>{

    public List<T> findByDslParamMap(Map<String, Object> dslParamMap);

    public Page<T> findByDslParamMap(Map<String, Object> dslParamMap, Pageable pageable);

}
