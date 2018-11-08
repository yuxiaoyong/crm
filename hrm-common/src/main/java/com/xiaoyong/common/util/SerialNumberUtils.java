package com.xiaoyong.common.util;/**
 * Created by atlantisholic on 2018/6/14.
 */

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.Random;

/**
 * @ClassName SerialNumberUtils
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/6/14 10:55
 * @Version 1.0.0
 **/
public final class SerialNumberUtils {

    private SerialNumberUtils(){}

    public static String generate(){
        return DateFormatUtils.format(new Date(), "yyyyMMddHHmmss")+(new Random().nextInt(900000)+100000);
    }

}
