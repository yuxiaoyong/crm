package com.xiaoyong.hrm.support.service;
/**
 * Created by atlantisholic on 2018/5/16.
 */

import com.xiaoyong.hrm.support.domain.BaseEntity;
import com.xiaoyong.hrm.support.domain.BaseRepository;
import org.apache.commons.lang3.StringUtils;
import org.reflections.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OneToMany;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @ClassName BaseServiceImpl
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/5/16 17:43
 * @Version 1.0.0
 **/
public abstract class BaseServiceImpl<T extends BaseEntity, ID> implements BaseService<T, ID>{

    @Autowired
    protected BaseRepository<T, ID> repository;

    @Transactional
    @Override
    public T save(T entity){
        if(entity.getId() == null || entity.getId() == 0){
            return create(entity);
        }else{
            return update(entity);
        }
    }

    @Transactional
    @Override
    public T create(T entity){
        try {
            setEntityCascade(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repository.save(entity);
    }

    @Transactional
    @Override
    public T update(T entity){
        try {
            setEntityCascade(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repository.save(entity);
    }

    private void setEntityCascade(T entity) throws Exception{
        Set<Field> fields = ReflectionUtils.getAllFields(entity.getClass(), ReflectionUtils.withType(List.class));
        for(Field field: fields){
            Set<Method> methods = ReflectionUtils.getAllMethods(entity.getClass(),
                    ReflectionUtils.withName("get"+ StringUtils.capitalize(field.getName())), ReflectionUtils.withAnnotation(OneToMany.class));
            if(methods.size() == 0) continue;
            field.setAccessible(true);
            Collection refers = (Collection)field.get(entity);
            if(refers == null) continue;
            for(Object refer: refers){
                setReferFields(refer, entity);
            }
        }

    }

    private void setReferFields(Object refer, T entity) throws Exception{
        Set<Field> fields = ReflectionUtils.getAllFields(refer.getClass(), ReflectionUtils.withType(entity.getClass()));
        for(Field field: fields){
            field.setAccessible(true);
            field.set(refer, entity);
        }
    }

    @Transactional
    @Override
    public void deleteById(ID id){
        Optional<T> result = repository.findById(id);
        if(result.isPresent()){
            result.get().setDeleted(true);
        }
    }

    @Transactional
    @Override
    public void deleteByIds(ID[] ids) {
        for(ID id: ids){
            deleteById(id);
        }
    }

    @Override
    public T findById(ID id){
        Optional<T> result = repository.findById(id);
        if(result.isPresent() && !result.get().isDeleted()){
            return result.get();
        }
        return null;
    }

    @Override
    public List<T> findByExample(T entity) {
        return repository.findAll(Example.of(entity, getExampleMatcher(entity)));
    }

    @Override
    public List<T> findByExample(T entity, Sort sort) {
        return repository.findAll(Example.of(entity, getExampleMatcher(entity)), sort);
    }

    @Override
    public Page<T> findByExample(T entity, Pageable pageable) {
        return repository.findAll(Example.of(entity, getExampleMatcher(entity)), pageable);
    }

    @Override
    public Page<T> findByExample(Example<T> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    private ExampleMatcher getExampleMatcher(T entity){
        return ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnorePaths("order", "createTime", "updateTime");
    }

    @Override
    public List<T> findByDslParamMap(Map<String, Object> params) {
        return repository.findByDslParamMap(params);
    }

    @Override
    public List<T> findByDslParamMap(Map<String, Object> params, Sort sort) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public Page<T> findByDslParamMap(Map<String, Object> params, Pageable pageable) {
        return repository.findByDslParamMap(params, pageable);
    }

    private Specification<T> createSpecification(Object model){
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.<Boolean>get("deleted"), Boolean.FALSE));
                return query.where(predicates.toArray(new Predicate[0])).getRestriction();
            }
        };
    }
}
