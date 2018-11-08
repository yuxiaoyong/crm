package com.xiaoyong.hrm.support.domain;/**
 * Created by atlantisholic on 2018/9/13.
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @ClassName BaseEntityListener
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/13 23:11
 * @Version 1.0.0
 **/
@Component
public class BaseEntityListener implements ApplicationContextAware{

    private static EncryptHandler encryptHandler = null;

    @PostLoad
    public void touchPostLoad(Object target){
        //TODO 处理解密业务
        if(encryptHandler != null)
            encryptHandler.decrypt(target);
    }

    @PrePersist
    public void touchPrePersist(Object target) {
        if(target == null || !(target instanceof BaseEntity)) return;
        touchOrder(target);
        touchTenant(target);
        //TODO 处理加密业务
        if(encryptHandler != null)
            encryptHandler.encrypt(target);
    }

    @PostPersist
    public void touchPostPersist(Object target){

    }

    @PreUpdate
    public void touchPreUpdate(Object target) {
        if(target == null || !(target instanceof BaseEntity)) return;
        touchOrder(target);
        touchTenant(target);
        //TODO 处理加密业务
        if(encryptHandler != null)
            encryptHandler.encrypt(target);
    }

    @PostUpdate
    public void touchPostUpdate(Object target){

    }

    /**
     * 为没有设置order的对象设置排序默认值
     * @param target
     */
    private void touchOrder(Object target){
        BaseEntity entity = (BaseEntity)target;
        if(entity.getOrder() == null){
            entity.setOrder(0);
        }
    }

    /**
     * 为没有设置租户信息的对象设置租户默认值
     * @param target
     */
    private void touchTenant(Object target){
        BaseEntity entity = (BaseEntity)target;
        if(entity.getTenantId() == null){
            entity.setTenantId(1);
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BaseEntityListener.encryptHandler = applicationContext.getBean(EncryptHandler.class);
    }
}
