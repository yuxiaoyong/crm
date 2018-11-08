package com.xiaoyong.hrm.support.domain;/**
 * Created by atlantisholic on 2018/9/19.
 */

import org.reflections.ReflectionUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Set;

/**
 * @ClassName DefaultEncryptHandler
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/19 21:16
 * @Version 1.0.0
 **/
@Component
public class DefaultEncryptHandler implements EncryptHandler {

    @Override
    public void encrypt(Object value) {
        Set<Field> fields = ReflectionUtils.getAllFields(value.getClass(), ReflectionUtils.withAnnotation(Encryption.class));
        if(fields.isEmpty()) return;

        fields.forEach((field) -> {
            field.setAccessible(true);
            try {
                Object data = field.get(value);
                field.set(value, Base64.getEncoder().encodeToString(data.toString().getBytes("UTF-8")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void decrypt(Object value) {
        Set<Field> fields = ReflectionUtils.getAllFields(value.getClass(), ReflectionUtils.withAnnotation(Encryption.class));
        if(fields.isEmpty()) return;

        fields.forEach((field) -> {
            field.setAccessible(true);
            try {
                Object data = field.get(value);
                field.set(value, new String(Base64.getDecoder().decode(data.toString()), "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
