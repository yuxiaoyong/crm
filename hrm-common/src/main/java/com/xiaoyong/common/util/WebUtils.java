/**
 * Creation Date:2016年11月11日-下午1:27:37
 * 
 * Copyright 2010-2016 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.common.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Description Of The Class<br/>
 * QQ:603470086
 * 
 * @author 	郁晓勇
 * @version 1.0.0, 2016年11月11日-下午1:27:37
 * @since 2016年11月11日-下午1:27:37
 */
public final class WebUtils {

	private WebUtils() {}
	/**
	 * 获取请求的完整路径，包括请求的链接地址
	 * 
	 * @author 	郁晓勇
	 * @version 1.0.0, 2016年12月14日-上午9:23:52
	 * @param request
	 * @return String
	 */
	public static String getRequestFullUrl(HttpServletRequest request){
		if(request == null){
			return null;
		}
		String url = request.getRequestURI().toString();
		if(!StringUtils.isEmpty(request.getQueryString())){
			url += "?" + request.getQueryString();
		}
		return url;
	}
	/**
	 * 判断是否是在ajax请求
	 * 
	 * @author 	郁晓勇
	 * @version 1.0.0, 2016年11月11日-下午1:30:35
	 * @param request
	 * @return boolean
	 */
	public static boolean isAjaxRequest(HttpServletRequest request){
		return StringUtils.startsWithIgnoreCase(request.getHeader("X-Requested-With"), "XMLHttpRequest");
	}
	
	/**
	 * 向http response中回写数据
	 * 
	 * @author 	郁晓勇
	 * @version 1.0.0, 2016年11月17日-下午2:21:19
	 * @param response
	 * @param value
	 * @throws Exception void
	 */
	public static void writeJsonString(HttpServletResponse response, String value) throws Exception{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(value);
		writer.flush();
	}	
	
	/**
	 * 获取客户端的真实地址
	 * 
	 * @author 	郁晓勇
	 * @version 1.0.0, 2016年11月17日-下午2:23:26
	 * @param request
	 * @return String
	 */
	public static String getClientRealIP(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }
	    return ip;  
	}
	
	
	public static void setExportHeader(String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		if (request.getHeader("User-Agent").indexOf("MSIE") != -1) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} else if (request.getHeader("User-Agent").indexOf("Mozilla/5.0") != -1) {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		} else if (request.getHeader("User-Agent").indexOf("Mozilla/4.0") != -1) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
	}
	
	public static void main(String[] args) {
		System.out.println(FilenameUtils.getExtension("aaa.xlsx"));
	}
}

