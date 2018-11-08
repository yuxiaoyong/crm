package com.xiaoyong.common.xss;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @ClassName XssHttpServletRequestWrapper
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/19 20:26
 * @Version 1.0.0
 **/
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

	@Override
    public String getParameter(String parameter) {
        return cleanXSS(super.getParameter(parameter));

    }

    @Override
    public String getHeader(String name) {
        return cleanXSS(super.getHeader(name));

    }

    private String cleanXSS(String value) {
    	if(StringUtils.isBlank(value)){
    		return value;
    	}
        return Jsoup.clean(value, Whitelist.basicWithImages());

    }

}
