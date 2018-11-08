package com.xiaoyong.hrm.support.domain;
/**
 * Created by atlantisholic on 2018/9/19.
 */

/**
 * @ClassName EncryptHandler
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/19 20:26
 * @Version 1.0.0
 **/
public interface EncryptHandler {

    /**
     * 加密对象中的某些字段
     * @param value
     * @return
     */
    void encrypt(Object value);

    /**
     * 解密对象中的某些字段
     * @param value
     * @return
     */
    void decrypt(Object value);

}
