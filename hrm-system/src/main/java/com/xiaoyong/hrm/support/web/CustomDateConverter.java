package com.xiaoyong.hrm.support.web;/**
 * Created by atlantisholic on 2018/5/30.
 */

import com.xiaoyong.common.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName CustomDateConverter
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/5/30 13:43
 * @Version 1.0.0
 **/
@Component
public class CustomDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        if(StringUtils.isBlank(s)){
            return null;
        }
        if(NumberUtils.isNumber(s)){
            return new Date(Long.valueOf(s));
        }
        try {
            return DateUtils.parseDate(s, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd"});
        } catch (ParseException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }
}
