package com.xiaoyong.hrm.support.domain;/**
 * Created by atlantisholic on 2018/5/21.
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseRepositoryImpl
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/5/21 16:27
 * @Version 1.0.0
 **/
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements  BaseRepository<T, ID> {

    private final EntityManager entityManager;

    private HqlQueryBuilder hqlQueryBuilder;

    BaseRepositoryImpl(JpaEntityInformation entityInformation,
                     EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        hqlQueryBuilder = new HqlQueryBuilder(entityManager);
    }

    public <S extends T> S save(S entity) {
        return super.save(entity);
    }

    public List<T> findByDslParamMap(Map<String, Object> dslParamMap){
        return hqlQueryBuilder.buildListQuery(getDomainClass(), dslParamMap, null).getResultList();
    }

    public Page<T> findByDslParamMap(Map<String, Object> dslParamMap, Pageable pageable){
        List<T> content = hqlQueryBuilder.buildListQuery(getDomainClass(), dslParamMap, pageable).getResultList();
        Long total = hqlQueryBuilder.buildCountQuery(getDomainClass(), dslParamMap).getSingleResult();
        return new PageImpl<T>(content, pageable, total);
    }


}
