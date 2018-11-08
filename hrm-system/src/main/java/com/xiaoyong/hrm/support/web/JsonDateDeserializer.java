package com.xiaoyong.hrm.support.web;/**
 * Created by atlantisholic on 2018/6/28.
 */


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.xiaoyong.common.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName JsonDateDeserializer
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/6/28 16:37
 * @Version 1.0.0
 **/
public class JsonDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String s = jsonParser.getText();
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
