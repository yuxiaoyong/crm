package com.xiaoyong.common.exception;/**
 * Created by atlantisholic on 2018/5/22.
 */

/**
 * @ClassName BadRequestException
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/5/22 15:04
 * @Version 1.0.0
 **/
public class BadRequestException extends BusinessException{

    public BadRequestException() {}

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
