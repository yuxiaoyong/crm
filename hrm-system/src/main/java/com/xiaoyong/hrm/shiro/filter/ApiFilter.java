package com.xiaoyong.hrm.shiro.filter;/**
 * Created by atlantisholic on 2018/8/12.
 */

import com.xiaoyong.common.util.WebUtils;
import com.xiaoyong.hrm.support.model.FailureResult;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ApiFilter
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/12 14:36
 * @Version 1.0.0
 **/
public class ApiFilter extends UserFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        if(super.isAccessAllowed(request, response, mappedValue)){
            return true;
        }

        if(((HttpServletRequest) request).getMethod().equals("OPTIONS")){
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = ((HttpServletResponse) response);

        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Request-Headers", "*");
        try {
            WebUtils.writeJsonString((HttpServletResponse) response,
                    FailureResult.create("用户会话已失效", "用户会话已失效", HttpServletResponse.SC_UNAUTHORIZED).toJsonString());
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return false;
    }

}
