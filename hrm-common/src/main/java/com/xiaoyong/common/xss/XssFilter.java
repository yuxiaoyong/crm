package com.xiaoyong.common.xss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName XssFilter
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/19 20:26
 * @Version 1.0.0
 **/
public class XssFilter implements Filter {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

    private List<String> excludes = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	if(logger.isInfoEnabled()){
			logger.info("==================== xss filter init ====================");
		}
		String temp = filterConfig.getInitParameter("excludes");
		if (temp != null) {
			String[] url = temp.split(",");
			for (int i = 0; url != null && i < url.length; i++) {
				excludes.add(url[i]);
			}
		}

    }

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        if(handleExcludeURL(request)){
  			chain.doFilter(request, response);
		}else {
            chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
        }
    }
    
    private boolean handleExcludeURL(ServletRequest request) {
    	
    	HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    	
		if (excludes == null || excludes.isEmpty()) {
			return false;
		}
 
		String url = httpServletRequest.getServletPath();
		for (String pattern : excludes) {
			Pattern p = Pattern.compile("^" + pattern);
			Matcher m = p.matcher(url);
			if (m.find()) {
				return true;
			}
		}
 
		return false;
	}


	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}
    
}
